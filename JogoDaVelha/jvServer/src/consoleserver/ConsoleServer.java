package consoleserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ConsoleServer {    
    private Socket socketClient;
    private ServerSocket serversocket;


    int numJogadas = 0;
    

    public boolean connect() {
        try {
            socketClient = serversocket.accept();  // fase de conexao
            return true;
        } catch (IOException e) {
            System.err.println("Nao fez conexao" + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            ConsoleServer servidor = new ConsoleServer();
            servidor.rodarServidor();
        } catch(Exception e){
            e.printStackTrace();
        }
    } 
    
    public void rodarServidor() throws Exception {
        String textoRecebido = "";
        String textoEnviado = "";
        Scanner input = new Scanner(System.in);

        serversocket = new ServerSocket(9500);
        System.out.println("Servidor iniciado!");

        boolean conectado = connect();

        while (conectado && !textoRecebido.trim().equals("sair")) {
            String[] StrA;
            textoRecebido = Conexao.receber(socketClient); //recebe
            System.out.print("\nMensagem Recebida: "+textoRecebido+"\n");
            
            numJogadas++;
            StrA = textoRecebido.split(";");
            System.out.println(" "+StrA[0]+" | "+StrA[1]+" | "+StrA[2]+" \n --|---|-- \n "+StrA[3]+" | "+StrA[4]+" | "+StrA[5]+" \n --|---|-- \n "+StrA[6]+" | "+StrA[7]+" | "+StrA[8]+" ");
            System.out.println(numJogadas);
            
            System.out.print("\nDigite a sua mensagem: ");// tratamento de dados
            textoEnviado = input.nextLine();
            
            if(textoEnviado.equals("1") && StrA[0].equals("-")){
                StrA[0] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("2") && StrA[1].equals("-")){
                StrA[1] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("3") && StrA[2].equals("-")){
                StrA[2] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("4") && StrA[3].equals("-")){
                StrA[3] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("5") && StrA[4].equals("-")){
                StrA[4] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("6") && StrA[5].equals("-")){
                StrA[5] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("7") && StrA[6].equals("-")){
                StrA[6] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("8") && StrA[7].equals("-")){
                StrA[7] = "S";
                numJogadas++;
            }else if(textoEnviado.equals("9") && StrA[8].equals("-")){
                StrA[8] = "S";
                numJogadas++;
            }else{
                System.out.println("Mensagem invalida");
            }
            
            
            System.out.println(" "+StrA[0]+" | "+StrA[1]+" | "+StrA[2]+" \n --|---|-- \n "+StrA[3]+" | "+StrA[4]+" | "+StrA[5]+" \n --|---|-- \n "+StrA[6]+" | "+StrA[7]+" | "+StrA[8]+" ");
            System.out.println("\n Numero de Jogadas: "+numJogadas);
            
            if(textoEnviado.equals("sair")){
            }else{
                textoEnviado = StrA[0]+";"+StrA[1]+";"+StrA[2]+";"+StrA[3]+";"+StrA[4]+";"+StrA[5]+";"+StrA[6]+";"+StrA[7]+";"+StrA[8];
                if(verificaVitoria(StrA)){
                    textoRecebido = "sair";
                }
            }
            Conexao.enviar(socketClient, textoEnviado); //Envia    

        }
        socketClient.close();
    }
    
    public boolean verificaVitoria(String[] StrA){
        if(//Horizontal C
            (StrA[0].equals("C") && StrA[1].equals("C") && StrA[2].equals("C"))
            || (StrA[3].equals("C") && StrA[4].equals("C") && StrA[5].equals("C"))
            || (StrA[6].equals("C") && StrA[7].equals("C") && StrA[8].equals("C"))
            || //Vertical
               (StrA[0].equals("C") && StrA[3].equals("C") && StrA[6].equals("C"))
            || (StrA[1].equals("C") && StrA[4].equals("C") && StrA[7].equals("C"))
            || (StrA[2].equals("C") && StrA[5].equals("C") && StrA[8].equals("C"))
            || //DIAGONAL
               (StrA[0].equals("C") && StrA[4].equals("C") && StrA[8].equals("C"))
            || (StrA[2].equals("C") && StrA[4].equals("C") && StrA[6].equals("C"))){
            System.out.println("VITORIA DO CLIENTE");
            return true;
        }else
        if(//Horizontal S
            (StrA[0].equals("S") && StrA[1].equals("S") && StrA[2].equals("S"))
            || (StrA[3].equals("S") && StrA[4].equals("S") && StrA[5].equals("S"))
            || (StrA[6].equals("S") && StrA[7].equals("S") && StrA[8].equals("S"))
            || //Vertical
               (StrA[0].equals("S") && StrA[3].equals("S") && StrA[6].equals("S"))
            || (StrA[1].equals("S") && StrA[4].equals("S") && StrA[7].equals("S"))
            || (StrA[2].equals("S") && StrA[5].equals("S") && StrA[8].equals("S"))
            || //DIAGONAL
                //HORIZONTAL
               (StrA[0].equals("S") && StrA[4].equals("S") && StrA[8].equals("S"))
            || (StrA[2].equals("S") && StrA[4].equals("S") && StrA[6].equals("S"))){
            System.out.println("VITORIA DO SERVIDOR");
            return true;

        }else
        if(numJogadas==9){
            System.out.println("EMPATE");
            return true;
        }
        return false;
    }
    
}
