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
		
		//Regras de negócio Gerais
		
do {
			
			acrescimo = 0;
			
			nome = JOptionPane.showInputDialog("Informe o nome: ");

			anonasc = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano do seu nascimento: "));

			estado = JOptionPane.showInputDialog("Informe a sua UF: ").toUpperCase();

			Object[] categoria = { "Empregado", "Empregador", "Desempregado" };

			escolha = JOptionPane.showInputDialog(null, "Qual a sua categoria?", "Título", JOptionPane.PLAIN_MESSAGE,
					null, categoria, "");

			tempbene = Integer
					.parseInt(JOptionPane.showInputDialog("Digite a quantidade de meses que receberá o beneficio"));

			if (escolha.equals("Empregado")) {

				beneficio = Integer.parseInt(JOptionPane.showInputDialog("Digite um beneficio entre 1000 e 1800"));

				Object[] empregado = { "Sim", "Não" };
				escolha2 = JOptionPane.showInputDialog(null, "Você esta aposentado?", "Título",
						JOptionPane.PLAIN_MESSAGE, null, empregado, "");

				if (escolha2.equals("Sim")) {

					aposentado = true;

				} else if (escolha2.equals("Não")) {

					aposentado = false;

				}

				if ((beneficio < 1000) && (beneficio > 1800)) {

					throw new IllegalArgumentException("O valor digitado é invalido (minimo de 1000 e maximo de 1800)");

				}

			} else if (escolha.equals("Empregador")) {

				funcionarios = Integer.parseInt(JOptionPane.showInputDialog("Quantos Funcionarios você possui?"));

				beneficio = funcionarios * 200;

				tempbene = BeneficioEmpregador(escolha, tempbene, funcionarios);

			} else if (escolha.equals("Desempregado")) {

				reducaoBeneficio = BeneficioDesempregado(escolha, tempbene, beneficio);
				
				beneficio = Integer.parseInt(JOptionPane.showInputDialog("Digite um beneficio entre 1500 e 2300"));

				mesesdesem = Integer.parseInt(JOptionPane.showInputDialog("A quantos meses você esta desempregado??"));

				if ((beneficio < 1500) && (beneficio > 2300)) {

					throw new IllegalArgumentException("O valor digitado é invalido (minimo de 1500 e maximo de 2300)");

				}

			}

			if ((tempbene < 2) && (tempbene > 12)) {

				throw new IllegalArgumentException(
						"O Tempo de beneficio digitado é invalido (minimo de 2 meses maximo de 12)");

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
						 "O usuario " + nome + " é um Empregado, Aposentado, com " + idade + " anos, e terá um acrescimo de " + acrescimo
								+ " com um beneficio de " + beneficio + " durante " + tempbene + " meses");

			}else if ((escolha.equals("Empregado")) && (escolha2.equals("Não"))) {

				JOptionPane.showMessageDialog(null,
						 "O usuario " + nome + " é um Empregado, Não é Aposentado, tem " + idade + " anos, e terá um acrescimo de " + acrescimo
								+ " com um beneficio de " + beneficio + " durante " + tempbene + " meses");

				
			}else if (escolha.equals("Empregador")) {
			

				JOptionPane.showMessageDialog(null,
						"O usuario " + nome + " é um Empregador, com "+funcionarios+" funcionarios, tem " + idade + " anos, e terá um acrescimo de "
								 + acrescimo + " com um beneficio de " + beneficio + " durante " + tempbene + " meses");
				
			}else if (escolha.equals("Desempregado")) {
				
				JOptionPane.showMessageDialog(null,
						"O usuario " + nome + " é um Desempregado, com " + idade + " anos, está Desempregado a " + mesesdesem
								+ " meses, e terá um acrescimo de " + acrescimo + " com um beneficio de " + beneficio + " durante " + tempbene + " meses");
				
			}

			Object[] options = { "Sim", "Não" };
			continua = JOptionPane.showOptionDialog(null, "Deseja cadastrar outro Usuario?", "Opção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			usuarios++;

		} while (continua != 1);

		System.out.println("\n O total de usuarios é " + usuarios);
		System.out.println("\n O total de beneficiados é " + beneficiados);

		System.out.println("\n O usuario com o maior tempo de beneficio é " + maiortempoN);
		System.out.println("O usuario com o segundo maior tempo de beneficio é " +maiortempo2N);

		System.out.println("\n O usuario com o maior valor de beneficio é " + maiorvalorN);
		System.out.println("O usuario com o segundo maior valor de beneficio é "+maiorvalor2N);
		
		System.out.println("\n O total de beneficio concedido a todas as pessoas é " + totalconcedido);

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
