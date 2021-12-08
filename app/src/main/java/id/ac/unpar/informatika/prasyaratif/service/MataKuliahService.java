package id.ac.unpar.informatika.prasyaratif.service;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
     * @param listener Object (view) yang membutuhkan datanya
     */
    public void fetchMataKuliah(APIListener listener){
        if (isFetched){
            listener.onFetched(mataKuliahPerSemester);
        } else {
            (new Thread(() -> {
                kodeMataKuliah = new HashMap<>();
                mataKuliahPerSemester = new ArrayList<>();

                HttpURLConnection urlConnection = null;
                InputStream in = null;
                String json = "";
                try {
                    URL url = new URL("https://raw.githubusercontent.com/ftisunpar/data/master/prasyarat.json");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(10000);
//                urlConnection.getResponseCode(); //cek status code
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuilder sb = new StringBuilder();
                    int c = 0;
                    while ((c = in.read()) != -1) {
                        sb.append((char) c);
                    }
                    json = sb.toString();

                    JSONArray arrayMatKul = new JSONArray(json);
                    for(int i = 0; i<arrayMatKul.length(); i++){
                        JSONObject mkobj = arrayMatKul.getJSONObject(i);
                        int semester = mkobj.getInt("semester");

                        JSONObject prasyaratObj = mkobj.getJSONObject("prasyarat");
                        JSONArray prasTempuhJson = prasyaratObj.getJSONArray("tempuh");
                        JSONArray prasLulusJson = prasyaratObj.getJSONArray("lulus");
                        JSONArray prasBersamaanJson = prasyaratObj.getJSONArray("bersamaan");
                        if (semester > mataKuliahPerSemester.size()){
                            mataKuliahPerSemester.add(new ArrayList<>());
                        }
                        mataKuliahPerSemester.get(semester-1).add(new MataKuliah(
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
                        ));

                    }

                }  catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                    if (in != null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                isFetched = true;
                listener.onFetched(mataKuliahPerSemester);
            })).start();
        }
    }

    private List<MataKuliah> parsePrasyarat(JSONArray listKodePrasyarat ) throws JSONException {
        List<MataKuliah> prasyaratMK = new ArrayList<>();
        for (int j = 0; j<listKodePrasyarat.length(); j++){
            prasyaratMK.add(kodeMataKuliah.get(listKodePrasyarat.getString(j)));
        }
        return prasyaratMK;
    }

    public interface APIListener{
        public void onFetched(List<List<MataKuliah>> dataMataKuliahPerSemester);
    }
}
