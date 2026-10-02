import java.io.IOException;
import java.net.Socket;


public class  RequestProcessor  {


    private Socket socket ; 

     
      RequestProcessor(Socket socket){
        this.socket=socket; 

      }

  


public void process(){
        try { 
            HttpRequest request=  new HttpRequest(socket); 
            request.readClientRequest(socket);
             HttpContext  context= new HttpContext(socket); 
             

           HttpResponse  response =  new HttpResponse(socket); 
            
           if ("/".equals(request.getUrl())) {
            response.ok("ok");
        } else if (request.getUrl().endsWith(".html")) {
            response.ok("ok html");
            String url1 = request.getUrl();
            response.sendContent("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n", "hello world");
            response.sendFile(url1);
        } else if (request.getUrl().endsWith(".png")) {
            response.ok("ok png");
            String url1 = request.getUrl();
            response.sendContent("HTTP/1.1 200 OK\r\nContent-Type: image/png\r\n\r\n", "hello world");
            response.sendFile(url1);
        } else if (request.getUrl().endsWith(".jpeg") || request.getUrl().endsWith(".jpg")) {
            response.ok("ok jpeg/jpg");
            String url1 = request.getUrl();
            response.sendContent("HTTP/1.1 200 OK\r\nContent-Type: image/jpeg\r\n\r\n", "hello world");
            response.sendFile(url1);
        } else if (request.getUrl().endsWith(".css")) {
            response.ok("ok css");
            String url1 = request.getUrl();
            response.sendContent("HTTP/1.1 200 OK\r\nContent-Type: text/css\r\n\r\n", "hello world");
            response.sendFile(url1);
        } else {
            response.notFound("erreur");
        }
        
        
          
        } 
        catch (Exception e ) {
            e.printStackTrace();
        }


    }
} 
