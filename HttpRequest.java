import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.File;

/**
 * HttpRequest
 */
public class HttpRequest {
    private Socket socketserver;
    private String method;
    private String url; 

    HttpRequest(Socket socket){
        this.socketserver = socket;
    }


    // Méthode pour lire la requête client depuis le socket
    public void readClientRequest(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        
            String line = reader.readLine();
            //  rappler revien  
            if (line != null && !line.isEmpty()) {
                // Découper la ligne de requête pour extraire la méthode et l'URL
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    this.method = parts[0]; 
                    this.url = parts[1];    
                }
            }

            System.out.println("Requête HTTP reçue : " + line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public String getMethod() {
        return this.method;
    }

 
    public String getUrl() {
        String url2 = this.url.substring(1);
        File file = new File(url2);
        //System.out.println("l'url =" + url2);
        if(file.exists()){
            System.out.println("fichier exist");
            return this.url;
        }
        else{
            System.out.println("fichier not found");
            return this.url;
        }

    }
}
