package com.mikhaellopez.androidwebserver;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Mikhael LOPEZ on 14/12/2015.
 */
public class AndroidWebServer extends NanoHTTPD {

    public AndroidWebServer(int port) {
        super(port);
    }

    public AndroidWebServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        try {
            session.parseBody(new HashMap<String, String>());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }


        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }

        Log.i(AndroidWebServer.class.getSimpleName(),GsonUtils.createGsonString(parms));
        return newFixedLengthResponse( msg + "</body></html>\n" );
    }
}
