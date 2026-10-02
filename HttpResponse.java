
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.print.DocFlavor.STRING;


/**
 * HttpResponse
 */
public class HttpResponse {
    private Socket socketserver;
    
    HttpResponse(Socket socket){
        this.socketserver = socket;
    }


    
    public void ok(String message){   
         try { 
            OutputStream output = this.socketserver.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        String text = "HTTP/1.1 200 ok";

        writer.println(text);
        System.out.println(text);
        if(message == "ok"){
            this.socketserver.close();
        }
    }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void notFound(String message){
      
        try{
            OutputStream output = this.socketserver.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        String text = "HTTP/1.1 404 Not Found";
        writer.println(text);
        System.out.println(text);
        this.socketserver.close();
       
         }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendFile(String filename) {
        try {
            String url2 = filename.substring(1);
            File file = new File(url2);
            FileInputStream fileInput = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
    
            OutputStream output = this.socketserver.getOutputStream();
            
            System.out.println("Ouverture du fichier: " + filename);
    
            // Envoi du fichier en binaire
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
    
            fileInput.close();
            output.flush();
            output.close();
    
            System.out.println("Fichier envoyé avec succès: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
    

    public String sendContent(String contentType, String content) {
        try {
            OutputStream output = this.socketserver.getOutputStream();
    
            // Envoyer le type de contenu en texte
            String header = contentType + "\r\n";
            output.write(header.getBytes());
    
            // Envoyer le contenu en binaire
            byte[] contentBytes = content.getBytes();
            output.write(contentBytes);
    
            output.flush();
    
            System.out.println(header + new String(contentBytes));
            return "Content sent successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    
    
}