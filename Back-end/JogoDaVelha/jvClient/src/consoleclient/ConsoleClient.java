
package consoleclient;

import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    Socket socket;
    String[] StrA;
    int numJogadas = 0;
    String b0="-";
    String b1="-";
    String b2="-";
    String b3="-";
    String b4="-";
    String b5="-";
    String b6="-"; 
    String b7="-";
    String b8="-";
    
    public void comunicarComServidor() throws Exception {
        String textoRequisicao = "Conexao estabelecida";
        String textoRecebido = "";
        socket = new Socket("localhost", 9500);
        Scanner input = new Scanner(System.in);
        
        while (!textoRecebido.trim().equals("sair")) {
            
            System.out.println(" "+b0+" | "+b1+" | "+b2+" \n --|---|-- \n "+b3+" | "+b4+" | "+b5+" \n --|---|-- \n "+b6+" | "+b7+" | "+b8+" ");
            System.out.print("\nMarcar em qual casa? (1 a 9): ");
            textoRequisicao = input.nextLine();
            
            if(textoRequisicao.equals("1") && b0.equals("-")){
                b0 = "C";
            }else if(textoRequisicao.equals("2") && b1.equals("-")){
                b1 = "C";
            }else if(textoRequisicao.equals("3") && b2.equals("-")){
                b2 = "C";
            }else if(textoRequisicao.equals("4") && b3.equals("-")){
                b3 = "C";
            }else if(textoRequisicao.equals("5") && b4.equals("-")){
                b4 = "C";
            }else if(textoRequisicao.equals("6") && b5.equals("-")){
                b5 = "C";
            }else if(textoRequisicao.equals("7") && b6.equals("-")){
                b6 = "C";
            }else if(textoRequisicao.equals("8") && b7.equals("-")){
                b7 = "C";
            }else if(textoRequisicao.equals("9") && b8.equals("-")){
                b8 = "C";
            }else{
                System.out.println("Mensagem Invalida");
            }
            
            System.out.println(" "+b0+" | "+b1+" | "+b2+" \n --|---|-- \n "+b3+" | "+b4+" | "+b5+" \n --|---|-- \n "+b6+" | "+b7+" | "+b8+" ");

            if(textoRequisicao.equals("sair")){
            }else{
                textoRequisicao = b0+";"+b1+";"+b2+";"+b3+";"+b4+";"+b5+";"+b6+";"+b7+";"+b8;
                if(verificaVitoria(textoRequisicao.split(";"))){
                    textoRequisicao = "sair";
                }
            }
            // Enviar mensagem para o servidor
            Conexao.enviar(socket, textoRequisicao);
            System.out.println("Mensagem enviada");

            // Receber mensagem do servidor
            textoRecebido = Conexao.receber(socket);
            System.out.println("Servidor enviou: " + textoRecebido);

            StrA = textoRecebido.split(";");
            
            b0 = StrA[0];
            b1 = StrA[1];
            b2 = StrA[2];
            b3 = StrA[3];
            b4 = StrA[4];
            b5 = StrA[5];
            b6 = StrA[6];
            b7 = StrA[7];
            b8 = StrA[8];
        }
    }


    public static void main(String[] args) {
        try {
            ConsoleClient cliente = new ConsoleClient();
            cliente.comunicarComServidor();
        } catch(Exception e){
            e.printStackTrace();
        }
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
        }else if(numJogadas==9){
            System.out.println("EMPATE");
            return true;
        }
        return false;
    }
    
}
