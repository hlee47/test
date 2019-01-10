package org.androidtown.test;

import org.androidtown.test.models.PackageTrackingListModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SmartTrackingAPIClient {
    private final static String baseURL = "http://info.sweettracker.co.kr/api/v1/trackingInfo";
    private final String APIKey = "h68Tn1bRYxuikHqmiUNvUg";
    private String mInvoiceNo;
    private PackageTrackingListModel mPackageTrackingListModel;

    public PackageTrackingListModel getPackageTrackingList(String invoiceNo) {
        mInvoiceNo = invoiceNo;
        String urlString = baseURL + "?t_key=" + APIKey + "&t_code=01&t_invoice=" + mInvoiceNo;

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            // parse JSON
            mPackageTrackingListModel = parseJSON(json);
            mPackageTrackingListModel.setComplete(invoiceNo);
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("URL Connection failed");
            e.printStackTrace();
        } catch (JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
        }
        return  mPackageTrackingListModel;
    }

    private PackageTrackingListModel parseJSON(JSONObject json) throws JSONException {
        PackageTrackingListModel packageTrackingListModel = new PackageTrackingListModel();
        packageTrackingListModel.setSenderName(json.getString("senderName"));
        packageTrackingListModel.setReceiverName(json.getString("receiverName"));
        packageTrackingListModel.setRecipient(json.getString("recipient"));
        packageTrackingListModel.setComplete(json.getString("complete"));
        return packageTrackingListModel;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


}
