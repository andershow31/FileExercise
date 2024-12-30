package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		String[] lines = new String[] {"TV LED,1290.99,1","Video Game,350.50,3","Iphone X,900.00,2","Samsung Galaxy 9,850.00,2"}; //array que vai para o arquivo
		String fileName = "out\\summary.csv"; //nome do arquivo já com a pasta que será criada a frente
		
		File pathName = new File("out");//nome da pasta que será criada por meio da classe File
		
		while(!pathName.exists()) { //se não existir a pasta ele cria
			pathName.mkdir(); // este é o códi
			System.out.println("Pasta Criada com sucesso");
		}
		
		if (pathName.exists()){ //se a pasta existir ele cria o arquivo
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){// esse é o try with resources, dessa forma o buffered e o fileWriter serão fechados automaticamente
				// é melhor assim pq é uma mão pra fazer o fechamento
				for (String line: lines) {//for each pra escrever as linhas
					bw.write(line);//escreve as linhas
					bw.newLine();//vai para as próximas linhas
				}
			}
			
			catch(IOException e) { //sempre usamos o IOException quando tratamos com arquivos
				System.out.println("Error: " + e.getMessage());
			}
			
			//segundo try, para leitura do arquivo
			try ( BufferedReader br = new BufferedReader(new FileReader("out\\summary.csv"))){ //instancia o reader para ler o arquivo criado, mes caso do outro try
				String line = br.readLine(); //cria uma variável para as linhas
				
		
				while (line!=null) {//enquanto houver linhas ele vai ler
					String name;
					Double price;
					Integer qtd;
					
					String[] words = line.split(","); //split é um metodo de String, a informação vem do arquivo como string, então abaixo fizemos o casting do price e qtd
					name = words[0];
					price = Double.parseDouble(words[1]);//casting string to double
					qtd = Integer.parseInt(words[2]); //casting string to Integer
					
					Products product = new Products(name, price, qtd);
					System.out.println(product.getName() + ", " + product.totalValue());
					line = br.readLine(); //vai pra próxima linha
					}
				}
			
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		
	}

}
