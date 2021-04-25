package PROVA;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class PA {

	public static void main(String[] args) {
		//Dados de Entrada do programa

		String nome;
		String estado;
		
		String maiorvalorN = null;
		String maiorvalor2N = null;

		String maiortempoN = null;
		String maiortempo2N = null;

		LocalDate data = LocalDate.now();

		int maiortempo = 0;
		int maiortempo2 = 0;

		int anonasc;
		int idade;
		int tempbene = 0;
		int funcionarios = 0;
		int mesesdesem = 0;
		int continua;
		int usuarios = 0;
		int beneficiados = 0;

		double beneficio = 0;
		double totalconcedido = 0;
		double acrescimo = 0;
		double beneficioUF = 0;
		double reducaoBeneficio = 0;
		
		double maiorvalor = 0;
		double maiorvalor2 = 0;

		
		boolean tembeneficio = false;
		boolean aposentado;
		
		Object escolha = null;
		Object escolha2 = null;
		
		//Regras de neg�cio Gerais
		
do {
			
			acrescimo = 0;
			
			nome = JOptionPane.showInputDialog("Informe o nome: ");

			anonasc = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano do seu nascimento: "));

			estado = JOptionPane.showInputDialog("Informe a sua UF: ").toUpperCase();

			Object[] categoria = { "Empregado", "Empregador", "Desempregado" };

			escolha = JOptionPane.showInputDialog(null, "Qual a sua categoria?", "T�tulo", JOptionPane.PLAIN_MESSAGE,
					null, categoria, "");

			tempbene = Integer
					.parseInt(JOptionPane.showInputDialog("Digite a quantidade de meses que receber� o beneficio"));

			if (escolha.equals("Empregado")) {

				beneficio = Integer.parseInt(JOptionPane.showInputDialog("Digite um beneficio entre 1000 e 1800"));

				Object[] empregado = { "Sim", "N�o" };
				escolha2 = JOptionPane.showInputDialog(null, "Voc� esta aposentado?", "T�tulo",
						JOptionPane.PLAIN_MESSAGE, null, empregado, "");

				if (escolha2.equals("Sim")) {

					aposentado = true;

				} else if (escolha2.equals("N�o")) {

					aposentado = false;

				}

				if ((beneficio < 1000) && (beneficio > 1800)) {

					throw new IllegalArgumentException("O valor digitado � invalido (minimo de 1000 e maximo de 1800)");

				}

			} else if (escolha.equals("Empregador")) {

				funcionarios = Integer.parseInt(JOptionPane.showInputDialog("Quantos Funcionarios voc� possui?"));

				beneficio = funcionarios * 200;

				tempbene = BeneficioEmpregador(escolha, tempbene, funcionarios);

			} else if (escolha.equals("Desempregado")) {

				reducaoBeneficio = BeneficioDesempregado(escolha, tempbene, beneficio);
				
				beneficio = Integer.parseInt(JOptionPane.showInputDialog("Digite um beneficio entre 1500 e 2300"));

				mesesdesem = Integer.parseInt(JOptionPane.showInputDialog("A quantos meses voc� esta desempregado??"));

				if ((beneficio < 1500) && (beneficio > 2300)) {

					throw new IllegalArgumentException("O valor digitado � invalido (minimo de 1500 e maximo de 2300)");

				}

			}

			if ((tempbene < 2) && (tempbene > 12)) {

				throw new IllegalArgumentException(
						"O Tempo de beneficio digitado � invalido (minimo de 2 meses maximo de 12)");

			}
			
			idade = data.getYear() - anonasc;
			
			beneficioUF = BeneficioEstados (estado, beneficio);
			acrescimo = acrescimo + beneficioUF;
			tembeneficio = ComBeneficio(tembeneficio, idade);

			if (!tembeneficio) {

				beneficio = 0;

			} else if (tembeneficio) {

				beneficiados++;

			}

			
			if ((beneficio > maiorvalor) && (tembeneficio)){
				
				maiorvalor2 = maiorvalor;
				
				maiorvalor2N = maiorvalorN;
				
				maiorvalor = beneficio;
				
				maiorvalorN = nome;
				
				
				
			}else if ((beneficio > maiorvalor2) && (tembeneficio)) {
				
				maiorvalor2 = beneficio;
				
				maiorvalor2N = nome;
				
			}
			
			
			if ((tempbene > maiortempo) && (tembeneficio)) {

				maiortempo2 = maiortempo;

				maiortempo2N = maiortempoN;

				maiortempo = tempbene;

				maiortempoN = nome;

			} else if ((tempbene > maiortempo2) && (tembeneficio)) {

				maiortempo2 = tempbene;

				maiortempo2N = nome;

			}

			totalconcedido += beneficio + acrescimo;

			if ((escolha.equals("Empregado")) && (escolha2.equals("Sim"))) {

				JOptionPane.showMessageDialog(null,
						 "O usuario " + nome + " � um Empregado, Aposentado, com " + idade + " anos, e ter� um acrescimo de " + acrescimo
								+ " com um beneficio de " + beneficio + " durante " + tempbene + " meses");

			}else if ((escolha.equals("Empregado")) && (escolha2.equals("N�o"))) {

				JOptionPane.showMessageDialog(null,
						 "O usuario " + nome + " � um Empregado, N�o � Aposentado, tem " + idade + " anos, e ter� um acrescimo de " + acrescimo
								+ " com um beneficio de " + beneficio + " durante " + tempbene + " meses");

				
			}else if (escolha.equals("Empregador")) {
			

				JOptionPane.showMessageDialog(null,
						"O usuario " + nome + " � um Empregador, com "+funcionarios+" funcionarios, tem " + idade + " anos, e ter� um acrescimo de "
								 + acrescimo + " com um beneficio de " + beneficio + " durante " + tempbene + " meses");
				
			}else if (escolha.equals("Desempregado")) {
				
				JOptionPane.showMessageDialog(null,
						"O usuario " + nome + " � um Desempregado, com " + idade + " anos, est� Desempregado a " + mesesdesem
								+ " meses, e ter� um acrescimo de " + acrescimo + " com um beneficio de " + beneficio + " durante " + tempbene + " meses");
				
			}

			Object[] options = { "Sim", "N�o" };
			continua = JOptionPane.showOptionDialog(null, "Deseja cadastrar outro Usuario?", "Op��o",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			usuarios++;

		} while (continua != 1);

		System.out.println("\n O total de usuarios � " + usuarios);
		System.out.println("\n O total de beneficiados � " + beneficiados);

		System.out.println("\n O usuario com o maior tempo de beneficio � " + maiortempoN);
		System.out.println("O usuario com o segundo maior tempo de beneficio � " +maiortempo2N);

		System.out.println("\n O usuario com o maior valor de beneficio � " + maiorvalorN);
		System.out.println("O usuario com o segundo maior valor de beneficio � "+maiorvalor2N);
		
		System.out.println("\n O total de beneficio concedido a todas as pessoas � " + totalconcedido);

	}

	public static int BeneficioEmpregado(Object escolha, int tempbene) {

        if (escolha.equals("Empregado"))
                {
            tempbene = 3;
                }
        return tempbene;
    }

    public static int BeneficioEmpregador(Object escolha, int tempbene, int funcionarios) {

        if (escolha.equals("Empregador") && (funcionarios <= 10)) {

            tempbene = 10;

        }
        return tempbene;

    }

    public static double BeneficioEstados(String estado, double beneficio) {

        double acrescimo = 0;
            	
        if(estado.equals("PA") || estado.equals("TO")) {

             acrescimo = beneficio * 0.09;
        
        }
        
        return acrescimo;
    }
        


    public static boolean ComBeneficio(boolean tembeneficio, int idade) {

        if(idade < 18) {

            tembeneficio = false;

        }else if(idade >= 18) {

            tembeneficio = true;

        }
        return tembeneficio;

    }

    public static double BeneficioDesempregado(Object escolha, int tempbene, double beneficio) {
    	          
        if (escolha.equals("Desempregado") && tempbene < 6) {
        	beneficio= beneficio - beneficio * 0.10;
        }
        return beneficio;



    }

}
