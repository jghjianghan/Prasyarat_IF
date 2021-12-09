package id.ac.unpar.informatika.prasyaratif.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

/**
 * Interactor untuk model MataKuliah
 * Bertugas mengambil data MataKuliah dari API, dan menganalisanya, juga menyimpannya selama aplikasi berjalan
 */
public class MataKuliahService {
    private Map<String, MataKuliah> kodeMataKuliah;
    private List<List<MataKuliah>> mataKuliahPerSemester;
    private volatile boolean isFetched = false;

    /**
     * Mengambil data ke API, lalu diberi ke listener
     * Jika data sudah pernah diambil, langsung diberikan ke listener
     *
     * @param listener Object (view) yang membutuhkan datanya
     */
    public void fetchMataKuliah(APIListener listener) {
        if (isFetched) {
            listener.onFetched(mataKuliahPerSemester);
        } else {
            //Fetch di thread baru
            (new Thread(() -> {
                kodeMataKuliah = new HashMap<>();
                mataKuliahPerSemester = new ArrayList<>();

                HttpURLConnection urlConnection = null;
                InputStream in = null;
                try {
                    URL url = new URL("https://raw.githubusercontent.com/ftisunpar/data/master/prasyarat.json");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(10000);
//                urlConnection.getResponseCode(); //cek status code

                    //baca hasil return
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuilder sb = new StringBuilder();
                    int c;
                    while ((c = in.read()) != -1) {
                        sb.append((char) c);
                    }

                    this.parseData(sb.toString());
                    this.analyzeData();

                    isFetched = true;
                    listener.onFetched(mataKuliahPerSemester);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            })).start();
        }
    }

    //Parse data json menjadi object
    private void parseData(String json) throws JSONException {
        JSONArray arrayMatKul = new JSONArray(json);
        for (int i = 0; i < arrayMatKul.length(); i++) {
            JSONObject mkobj = arrayMatKul.getJSONObject(i);
            int semester = mkobj.getInt("semester");

            JSONObject prasyaratObj = mkobj.getJSONObject("prasyarat");
            JSONArray prasTempuhJson = prasyaratObj.getJSONArray("tempuh");
            JSONArray prasLulusJson = prasyaratObj.getJSONArray("lulus");
            JSONArray prasBersamaanJson = prasyaratObj.getJSONArray("bersamaan");
            if (semester > mataKuliahPerSemester.size()) {
                mataKuliahPerSemester.add(new ArrayList<>());
            }
            MataKuliah mkbaru = new MataKuliah(
                    mkobj.getString("nama"),
                    mkobj.getString("kode"),
                    semester,
                    mkobj.getBoolean("wajib"),
                    mkobj.getInt("sks"),
                    parsePrasyarat(prasTempuhJson),
                    parsePrasyarat(prasLulusJson),
                    parsePrasyarat(prasBersamaanJson),
                    prasyaratObj.optInt("berlakuAngkatan"),
                    false
            );
            kodeMataKuliah.put(mkbaru.getKode(), mkbaru);
            mataKuliahPerSemester.get(semester - 1).add(mkbaru);
        }
    }

    //Menganalisa data untuk mendapat insight
    private void analyzeData() {
        List<MataKuliah> mkId = new ArrayList<>();
        Map<String, Integer> idOfKode = new HashMap<>();
        for (int i = 0; i < mataKuliahPerSemester.size(); i++) {
            for (int j = 0; j < mataKuliahPerSemester.get(i).size(); j++) {
                idOfKode.put(mataKuliahPerSemester.get(i).get(j).getKode(), mkId.size());
                mkId.add(mataKuliahPerSemester.get(i).get(j));
            }
        }
        //Build adjacency list
        int nVertex = mkId.size();
        List<List<Integer>> adjacencyListLulus = new ArrayList<>(nVertex);
        List<List<Integer>> adjacencyListTempuh = new ArrayList<>(nVertex);
        for (int i = 0; i < nVertex; i++) {
            adjacencyListLulus.add(new ArrayList<>());
            adjacencyListTempuh.add(new ArrayList<>());
        }
        for (int i = 0; i < nVertex; i++) {
            MataKuliah currMK = mkId.get(i);
            for (MataKuliah prasyarat : currMK.getPrasyaratLulus()) {
                adjacencyListLulus.get(idOfKode.get(prasyarat.getKode()))
                        .add(idOfKode.get(currMK.getKode()));
            }
            for (MataKuliah prasyarat : currMK.getPrasyaratTempuh()) {
                adjacencyListLulus.get(idOfKode.get(prasyarat.getKode()))
                        .add(idOfKode.get(currMK.getKode()));
            }
        }
        //Simpan jumlah edge keluar
        for (int i = 0; i < nVertex; i++) {
            mkId.get(i).setPrasyaratLulusBagi(adjacencyListLulus.get(i).size());
            mkId.get(i).setPrasyaratTempuhBagi(adjacencyListTempuh.get(i).size());
        }

        // DFS
        boolean[] visited = new boolean[nVertex];
        for (int i = 0; i < nVertex; i++) {
            if (!visited[i]) {
                this.traverseGraph(i, visited, mkId, adjacencyListLulus, adjacencyListTempuh);
            }

        }
    }

    /**
     * Melakukan traversal pada graf prasyarat secara dfs untuk menghitung maximum reachable depth-nya
     * @param currentVertex Vertex yang sedang dikunjungi saat ini
     * @param isVisited Array boolean yang menandai apakah vertex sudah dikunjungi atau belum
     * @param mkId List mata kuliah secara terurut
     * @param adjacencyListLulus Adjacency list berdasarkan syarat lulus
     * @param adjacencyListTempuh Adjacency list berdasarkan syarat tempuh
     */
    private void traverseGraph(int currentVertex, boolean[] isVisited, List<MataKuliah> mkId, List<List<Integer>> adjacencyListLulus, List<List<Integer>> adjacencyListTempuh) {
        isVisited[currentVertex] = true;
        int maxDepth = 0;
        for (int neighbor : adjacencyListLulus.get(currentVertex)) {
            if (!isVisited[neighbor]) {
                traverseGraph(neighbor, isVisited, mkId, adjacencyListLulus, adjacencyListTempuh);
            }
            maxDepth = Math.max(maxDepth, 1 + mkId.get(neighbor).getPanjangRantaiKeBawah());
        }
        for (int neighbor : adjacencyListTempuh.get(currentVertex)) {
            if (!isVisited[neighbor]) {
                traverseGraph(neighbor, isVisited, mkId, adjacencyListLulus, adjacencyListTempuh);
            }
            maxDepth = Math.max(maxDepth, 1 + mkId.get(neighbor).getPanjangRantaiKeBawah());
        }
        mkId.get(currentVertex).setPanjangRantaiKeBawah(maxDepth);
    }

    /**
     * Memproses prasyarat dalam bentuk JSONArray dan mengembalikannya sebagai List MataKuliah
     * @param listKodePrasyarat Daftar kode prasyarat
     * @return Daftar objek MataKuliah dalam bentuk List
     * @throws JSONException Jika terjadi error saat melakukan parsing JSON
     */
    private List<MataKuliah> parsePrasyarat(JSONArray listKodePrasyarat) throws JSONException {
        List<MataKuliah> prasyaratMK = new ArrayList<>();
        for (int j = 0; j < listKodePrasyarat.length(); j++) {
            prasyaratMK.add(kodeMataKuliah.get(listKodePrasyarat.getString(j)));
        }
        return prasyaratMK;
    }

    public interface APIListener {
        public void onFetched(List<List<MataKuliah>> dataMataKuliahPerSemester);
    }
}
