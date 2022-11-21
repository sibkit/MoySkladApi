package moysklad.core;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MsHttpManager
{
    private static MsHttpManager instance;
    public static MsHttpManager getInstance()
    {
        if(instance==null)
        {
            instance = new MsHttpManager();
            //instance.setLogin("admin@ledmaster_pro");
            //instance.setPassword("ledmaster@A571");
        }
        return instance;
    }

    //private String login;
    //private String password;


    public String executePut(String url, String body, MsUser user)
    {
        try
        {
            String authString = user.getLogin() + ":" + user.getPassword();
            byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
            String authStringEnc = new String(authEncBytes);


            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

            HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
            httpConnection.setRequestMethod("PUT");
            httpConnection.setDoOutput(true);

            byte[] out = body.getBytes(StandardCharsets.UTF_8);

            httpConnection.setFixedLengthStreamingMode(out.length);
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            httpConnection.connect();

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(out);
            outputStream.flush();
            outputStream.close();

            InputStream inputStream;

            try
            {
                inputStream = httpConnection.getInputStream();
            }
            catch (Exception ex)
            {

                InputStream errorStream = httpConnection.getErrorStream();
                if(errorStream!=null)
                {
                    ByteArrayOutputStream erResponse = new ByteArrayOutputStream();
                    byte[] buffer = new byte[512];
                    int length;
                    while ((length = httpConnection.getErrorStream().read(buffer)) != -1)
                        erResponse.write(buffer, 0, length);

                    return erResponse.toString(StandardCharsets.UTF_8);
                }
                else
                {
                    ex.printStackTrace();
                    return "Error";
                }

            }

            ByteArrayOutputStream osResponse = new ByteArrayOutputStream();

            byte[] buffer = new byte[512];
            int length;
            while ((length = inputStream.read(buffer)) != -1)
            {
                osResponse.write(buffer, 0, length);
            }

            return osResponse.toString("UTF-8");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "ERROR";
        }
    }


    public String executeGet(String url, MsUser user)
    {
        try
        {
            System.out.println(url);
            String authString = user.getLogin() + ":" + user.getPassword();
            byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
            String authStringEnc = new String(authEncBytes);


            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[512];
            int length;
            while ((length = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, length);
            }

            return outputStream.toString("UTF-8");
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
            return null;
        }
    }
/*
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

 */
}
