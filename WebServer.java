import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




class WebServer{
    private int port;
    
    WebServer(int port){ //construct
        this.port = port;
    }

        public void run(){
        boolean arret = false;
        //lance la méthode
        //faire boucle while qui interroge en continue le server
        while(!arret){
        try{
        ServerSocket socketserver = new ServerSocket(this.port);
        Socket socket = socketserver.accept();
        RequestProcessor request = new RequestProcessor(socket);
        request.process();
        socketserver.close();    
        }
        catch(IOException e){
            e.printStackTrace();
            arret = true;
        }
    }
    }


}