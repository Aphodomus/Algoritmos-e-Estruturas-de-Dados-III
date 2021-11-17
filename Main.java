import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import datastructures.HashExtensivel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.text.Normalizer;
import java.util.regex.Pattern;
import datastructures.ListaInvertida;
import java.util.Comparator;

class Clear {
    public static void main(String... arg) throws Exception, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Runtime.getRuntime().exec("clear");
        }
    }
}

class GradeSorter implements Comparator<Question>{
    @Override
    public int compare(Question o1, Question o2) {
        if(o1.getGrade() == o2.getGrade())
            return 0;
        return o1.getGrade() > o2.getGrade() ? -1 : 1;
    }
}

public class Main {
    // Static variables
    public static final Scanner scanner = new Scanner(System.in);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static ZoneId zone = ZoneId.of("America/Sao_Paulo");
    public static int IDUSER = -1;

    private static long getMillis(String strDate, DateTimeFormatter formatter, ZoneId zone) {
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, formatter);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        Instant instant = zonedDateTime.toInstant();
        long milli = instant.toEpochMilli();
        return milli;
    }

    private static String getDateString(long milli, DateTimeFormatter formatter, ZoneId zone) {
        Instant instant = Instant.ofEpochMilli(milli);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zone);
        String strDate = zonedDateTime.format(formatter);
        return strDate;
    }

    public static String removeAccents(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    static int[] intersectionArray(int[] arrayA, int[] arrayB){
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i = 0; i < arrayA.length; i++){
            for(int j = 0; j < arrayB.length; j++){
                if(arrayA[i] == arrayB[j]){
                    arrList.add(arrayA[i]);
                    j = arrayB.length;
                }
            }
        }
        int[] arrayC = new int[arrList.size()];
        for(int i = 0; i < arrayC.length; i++){
            arrayC[i] = arrList.get(i);
        }
        return arrayC;
    }

    static void printFormattedQuestion(Question tempQuestion, User tempUser){
        System.out.println("Pergunta: " + tempQuestion.getQuestion());
        System.out.print("Criada em " + getDateString(tempQuestion.getCreation(), formatter, zone));
        System.out.println(" por " + tempUser.getName());
        System.out.println("Palavras chave: " + tempQuestion.getKeyWords());
        System.out.println("Nota: " + tempQuestion.getGrade());
    }

    public static void printFormattedAnswer(Answer tempAnswer, User tempUser) {
        System.out.println("Resposta: " + tempAnswer.getAnswer());
        System.out.print("Criada em " + getDateString(tempAnswer.getCreation(), formatter, zone));
        System.out.println(" por " + tempUser.getName());
        System.out.println("Nota: " + tempAnswer.getGrade());
    }

    public static void telaPrincipal(User user) throws Exception {
        int option1, option2, option3 = 0;
        int count = 0;
        int choice = 0;
        String userquestion, keywords, confirmCreation, userAnswer = "";
        IDUSER = user.getID(); // Get the ID of the user and assign to a global variable
        
        try {
            CRUDQuestion question = new CRUDQuestion("questions");
            CRUDAnswer answer = new CRUDAnswer("answers");

            do {
                Clear.main();
                System.out.println("\n=======================================================");
                System.out.println("BEM-VINDO AO SISTEMA PRINCIPAL " + user.getName() + "");
                System.out.println("=======================================================\n");
                System.out.println("INICIO\n");
                System.out.println("1) Minha area");
                System.out.println("2) Buscar perguntas\n");
                System.out.println("0) Sair\n");
                System.out.print("Opcao: ");
    
                try {
                    option1 = Integer.valueOf(scanner.nextLine());
                } catch (NumberFormatException e) {
                    option1 = -1;
                }
    
                switch (option1) {
                    case 0: {
                        System.out.println("\nVoltando ao acesso.");
                        waitEnter();
                    } break;
    
                    case 1: {
                        do {
                            Clear.main();
                            System.out.println("\n======================================================");
                            System.out.println("BEM-VINDO AO SISTEMA PRINCIPAL " + user.getName() + "");
                            System.out.println("=======================================================\n");
                            System.out.println("INICIO > MINHA AREA\n");
                            System.out.println("1) Minhas perguntas");
                            System.out.println("2) Minhas respostas");
                            System.out.println("3) Meus votos em perguntas");
                            System.out.println("4) Meus votos em resposta");
                            System.out.println("0) Retornar ao menu anterior\n");
                            System.out.print("Opcao: ");
    
                            try {
                                option2 = Integer.valueOf(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                option2 = -1;
                            }
    
                            switch (option2) {
                                case 0: {
                                    System.out.println("\nVoltando ao inicio.");
                                    waitEnter();
                                } break;
    
                                case 1: {
                                    do {
                                        Clear.main();
                                        System.out.println("\n======================================================");
                                        System.out.println("BEM-VINDO AO SISTEMA PRINCIPAL " + user.getName() + "");
                                        System.out.println("=======================================================\n");
                                        System.out.println("INICIO > MINHA AREA > MINHAS PERGUNTAS\n");
                                        System.out.println("1) Listar");
                                        System.out.println("2) Incluir");
                                        System.out.println("3) Alterar");
                                        System.out.println("4) Arquivar");
                                        System.out.println("0) Retornar ao menu anterior\n");
                                        System.out.print("Opcao: ");
    
                                        try {
                                            option3 = Integer.valueOf(scanner.nextLine());
                                        } catch (NumberFormatException e) {
                                            option3 = -1;
                                        }
    
                                        switch (option3) {
                                            case 0: {
                                                System.out.println("\nVoltando a minha area.");
                                                waitEnter();
                                            } break;
    
                                            case 1: {
                                                Clear.main();
                                                System.out.println("\n=================");
                                                System.out.println("Minhas perguntas.");
                                                System.out.println("=================\n");

                                                ArrayList<ParIDUserIDQuestion> lista = question.list(IDUSER);

                                                for (int i = 0; i < lista.size(); i++) {
                                                    Question temp = question.read(lista.get(i).getIDQuestion());
                                                    
                                                    if (temp.getActive()) {
                                                        System.out.println((i + 1) + ". (Ativa)");
                                                        System.out.println("Data e hora: " + getDateString(temp.getCreation(), formatter, zone));
                                                        System.out.println("Pergunta: " + temp.getQuestion());
                                                        System.out.println("Palavras chave: " + temp.getKeyWords() + "\n");
                                                    } else {
                                                        System.out.println((i + 1) + ". (Arquivada)");
                                                        System.out.println("Data e hora: " + getDateString(temp.getCreation(), formatter, zone));
                                                        System.out.println("Pergunta: " + temp.getQuestion());
                                                        System.out.println("Palavras chave: " + temp.getKeyWords() + "\n");
                                                    }
                                                }

                                                waitEnter();
                                            } break;
    
                                            case 2: {
                                                Clear.main();
                                                System.out.println("\n=====================");
                                                System.out.println("Incluir uma pergunta.");
                                                System.out.println("=====================\n");

                                                System.out.print("Digite a sua pergunta: ");
                                                userquestion = removeAccents(scanner.nextLine()).trim();
                                                System.out.print("Digite as palavras chave da pergunta (separadas por espaco): ");
                                                keywords = removeAccents(scanner.nextLine()).trim(); // Lembrar de limpar a keyword, tirar acentos, espacos em branco e etc

                                                if (userquestion.length() != 0 && keywords.length() != 0) {
                                                    Clear.main();
                                                    System.out.println("Dados inseridos");
                                                    System.out.println("===============\n");
                                                    System.out.println("Pergunta -> " + userquestion);
                                                    System.out.println("Palavras chave -> " + keywords + "\n");
                                                    System.out.print("Deseja confirmar a criacao desta nova pergunta Sim | Nao ? ");
                                                    confirmCreation = scanner.nextLine();

                                                    if (confirmCreation.toLowerCase().equals("sim")) {
                                                        LocalDateTime now = LocalDateTime.now();
                                                        String nowFormatted = now.format(formatter);
                                                        long milli = getMillis(nowFormatted, formatter, zone);                   

                                                        Question temp = new Question(IDUSER, milli, Short.parseShort("0"), userquestion, keywords);
                                                        question.create(IDUSER, temp);

                                                        waitEnter();

                                                        System.out.println("\n===========================");
                                                        System.out.println("Pergunta criada com sucesso.");
                                                        System.out.println("===========================\n");
                                                        waitEnter();
                                                    } else {
                                                        System.out.println("\n=========================================");
                                                        System.out.println("Voce nao confirmou a criacao do usuario.");
                                                        System.out.println("=========================================\n");
                                                        waitEnter();
                                                    }
                                                } else {
                                                    System.out.println("\n=========================================================");
                                                    System.out.println("Voce digitou uma pergunta ou as palavras chaves em branco.");
                                                    System.out.println("=========================================================\n");
                                                    waitEnter();
                                                }
                                            } break;
    
                                            case 3: {
                                                Clear.main();
                                                System.out.println("\n=============================");
                                                System.out.println("Alterar uma de suas perguntas.");
                                                System.out.println("=============================\n");

                                                ArrayList<ParIDUserIDQuestion> lista = question.list(IDUSER);
                                                ArrayList<Integer> listaDeIDsQuestionAtivas = new ArrayList();

                                                for (int i = 0; i < lista.size(); i++) {
                                                    Question temp = question.read(lista.get(i).getIDQuestion());
                                                    
                                                    if (temp.getActive()) {
                                                        count++;
                                                        listaDeIDsQuestionAtivas.add(temp.getID());
                                                        System.out.println((i + 1) + ". (Ativas)");
                                                        System.out.println("Data e hora: " + getDateString(temp.getCreation(), formatter, zone));
                                                        System.out.println("Pergunta: " + temp.getQuestion() + "?");
                                                        System.out.println("Palavras chave: " + temp.getKeyWords() + "\n");
                                                    }
                                                }

                                                do {
                                                    System.out.print("Escolha uma das perguntas: ");
                                                    choice = Integer.parseInt(scanner.nextLine());
                                                } while (choice >= count + 1);

                                                Question questionChoice = question.read(listaDeIDsQuestionAtivas.get(choice - 1));

                                                System.out.print("Digite a nova pergunta: ");
                                                userquestion = removeAccents(scanner.nextLine()).trim();
                                                System.out.print("Digite as novas palavras chave: ");
                                                keywords = removeAccents(scanner.nextLine()).trim();

                                                if (userquestion.length() != 0 && keywords.length() != 0) {
                                                    Clear.main();
                                                    System.out.println("Dados inseridos");
                                                    System.out.println("===============\n");
                                                    System.out.println("Pergunta -> " + userquestion);
                                                    System.out.println("Palavras chave -> " + keywords + "\n");
                                                    System.out.print("Deseja confirmar a atualizacao desta nova pergunta Sim | Nao ? ");
                                                    confirmCreation = scanner.nextLine();

                                                    if (confirmCreation.toLowerCase().equals("sim")) {
                                                        question.deleteKeywords(questionChoice);

                                                        questionChoice.setQuestion(userquestion);
                                                        questionChoice.setKeyWords(keywords);
                                                        LocalDateTime now = LocalDateTime.now();
                                                        String nowFormatted = now.format(formatter);
                                                        long milli = getMillis(nowFormatted, formatter, zone);   
                                                        questionChoice.setCreation(milli);

                                                        question.updateQuestion(questionChoice);

                                                        System.out.println("\n================================");
                                                        System.out.println("Pergunta atualizada com sucesso.");
                                                        System.out.println("================================\n");
                                                        waitEnter();
                                                    } else {
                                                        System.out.println("\n=========================================");
                                                        System.out.println("Voce nao confirmou a atualizacao da pergunta.");
                                                        System.out.println("=========================================\n");
                                                        waitEnter();
                                                    }
                                                }
                                            } break;
    
                                            case 4: {
                                                Clear.main();
                                                System.out.println("\n=============================");
                                                System.out.println("Arquivar uma de suas perguntas.");
                                                System.out.println("=============================\n");

                                                ArrayList<ParIDUserIDQuestion> lista = question.list(IDUSER);
                                                ArrayList<Integer> listaDeIDsQuestionAtivas = new ArrayList();

                                                for (int i = 0; i < lista.size(); i++) {
                                                    Question temp = question.read(lista.get(i).getIDQuestion());
                                                    
                                                    if (temp.getActive()) {
                                                        count++;
                                                        listaDeIDsQuestionAtivas.add(temp.getID());
                                                        System.out.println((i + 1) + ". (Ativas)");
                                                        System.out.println("Data e hora: " + getDateString(temp.getCreation(), formatter, zone));
                                                        System.out.println("Pergunta: " + temp.getQuestion() + "?");
                                                        System.out.println("Palavras chave: " + temp.getKeyWords() + "\n");
                                                    }
                                                }

                                                do {
                                                    System.out.print("Escolha uma das perguntas: ");
                                                    choice = Integer.parseInt(scanner.nextLine());
                                                } while (choice >= count + 1);

                                                Question questionChoice = question.read(listaDeIDsQuestionAtivas.get(choice - 1));
                                                
                                                Clear.main();
                                                System.out.println("Dados da pergunta");
                                                System.out.println("=================\n");
                                                System.out.println("Pergunta -> " + questionChoice.getQuestion());
                                                System.out.println("Palavras chave -> " + questionChoice.getKeyWords() + "\n");
                                                System.out.print("Deseja confirmar o arquivamento desta pergunta Sim | Nao ? ");
                                                confirmCreation = scanner.nextLine();

                                                if (confirmCreation.toLowerCase().equals("sim")) {
                                                    question.deleteKeywords(questionChoice);
                                                    questionChoice.setActive(false);
                                                    question.updateQuestion(questionChoice);

                                                    System.out.println("\n================================");
                                                    System.out.println("Pergunta arquivada com sucesso.");
                                                    System.out.println("================================\n");
                                                    waitEnter();
                                                } else {
                                                    System.out.println("\n============================================");
                                                    System.out.println("Voce nao confirmou o arquivamento da pergunta.");
                                                    System.out.println("============================================\n");
                                                    waitEnter();
                                                }
                                                
                                            } break;
    
                                            default:
                                                System.out.println("Opcao invalida.");
                                                waitEnter();
                                        }
                                    } while (option3 != 0);
                                } break;
    
                                case 2: {
    
                                } break;
    
                                case 3: {
    
                                } break;
    
                                case 4: {
    
                                } break;
    
                                default:
                                    System.out.println("Opcao invalida.");
                                    waitEnter();
                            }
                        } while (option2 != 0);
                    } break;
    
                    case 2: {
                        choice = 0;
                        count = 0;
                        String[] keyWordsArray;
                        int[] answerSet;
                        int[] tempSet;
                        ListaInvertida listaInvertida = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
                        ArrayList<Question> questionsArray = new ArrayList<Question>();
                        Question tempQuestion;
                        CRUDUser tempCrudUser = new CRUDUser("pessoas");
                        User tempUser;
                        
                        Clear.main();
                        System.out.println("\n======================================================");
                        System.out.println("BEM-VINDO AO SISTEMA PRINCIPAL " + user.getName() + "");
                        System.out.println("=======================================================\n");
                        System.out.println("INICIO > BUSCA\n");
                        System.out.println("Busque as perguntas por palavra chave separadas por ponto e virgula");
                        System.out.println("Ex: politica;Brasil;eleicoes");
                        System.out.print("\nPalavras chave: ");

                        keywords = removeAccents(scanner.nextLine()).trim();
                        keyWordsArray = keywords.split(";");
                        answerSet = listaInvertida.read(keyWordsArray[0]);
                        
                        for(int i = 1; i < keyWordsArray.length; i++){
                            tempSet = listaInvertida.read(keyWordsArray[i]);
                            answerSet = intersectionArray(answerSet, tempSet);
                        }

                        for(int i = 0; i < answerSet.length; i++){
                            tempQuestion = question.read(answerSet[i]);
                            if (tempQuestion.getActive()) {
                                questionsArray.add(tempQuestion);
                            }
                        }
                        questionsArray.sort(new GradeSorter());


                        Clear.main();
                        System.out.println("PERGUNTAS ENCONTRADAS\n");
                        System.out.print("PALAVRAS CHAVE:");
                        for (String a : keyWordsArray) System.out.print(" "+a+";");
                        System.out.println("\n");

                        if(questionsArray.size() > 0){
                            for(int i = 0; i < questionsArray.size(); i++){
                                tempQuestion = questionsArray.get(i);
                                tempUser = tempCrudUser.read(tempQuestion.getIDUser());
                                count++;
                                System.out.println((i + 1));
                                printFormattedQuestion(tempQuestion, tempUser);
                                System.out.println("");
                            }
                            do{
                                System.out.println("Que pergunta deseja visualizar (0 para voltar)?");
                                choice = Integer.parseInt(scanner.nextLine());
                            }while(choice > count);
                        }else{
                            System.out.println("Nenhuma pergunta encontrada.\n");
                            waitEnter();
                        }
                        
                        if(choice != 0){
                            Clear.main();
                            System.out.println("PERGUNTA "+choice);
                            System.out.println("=============\n");
                            System.out.println("INICIO > BUSCA > PERGUNTA SELECIONADA\n");
                            tempQuestion = questionsArray.get(choice-1);
                            tempUser = tempCrudUser.read(tempQuestion.getIDUser());
                            printFormattedQuestion(tempQuestion, tempUser);
                            System.out.println("\n");
                            System.out.println("RESPOSTAS");
                            System.out.println("---------");
                            
                            System.out.println("1");
                            waitEnter();

                            ArrayList<ParIDQuestionIDAnswer> lista = answer.listQuestion(tempQuestion.getID());

                            for (int i = 0; i < lista.size(); i++) {
                                System.out.println("question: " + lista.get(i).getIDQuestion());
                                System.out.println("answer: " + lista.get(i).getIDAnswer());
                            }

                            for (int i = 0; i < lista.size(); i++) {
                                Answer temp = answer.read(lista.get(i).getIDAnswer());
                                
                                System.out.println(i + 1 + ".");
                                printFormattedAnswer(temp, tempUser);
                            }

                            do{
                                System.out.println("1) Responder");
                                System.out.println("2) Avaliar a pergunta");
                                System.out.println("3) Avaliar uma resposta\n");
                                System.out.println("0) Retornar\n");
                                System.out.println("Opcao: ");

                                try{
                                    option3 = Integer.valueOf(scanner.nextLine());
                                }catch(NumberFormatException e){
                                    option3 = -1;
                                }
        
                                switch(option3){
                                    case 0:{
                                        System.out.println("\nVoltando ao inicio.");
                                        waitEnter();
                                    }break;
        
                                    case 1:{
                                        Clear.main();
                                        System.out.println("PERGUNTA "+choice);
                                        System.out.println("=============\n");
                                        System.out.println("INICIO > BUSCA > PERGUNTA SELECIONADA > RESPOSTA\n");

                                        printFormattedQuestion(tempQuestion, tempUser);

                                        System.out.print("\nResposta: ");
                                        userAnswer = scanner.nextLine();
                                        System.out.println("Sua resposta: " + userAnswer);
                                        LocalDateTime now = LocalDateTime.now();
                                        String nowFormatted = now.format(formatter);
                                        long milli = getMillis(nowFormatted, formatter, zone);   

                                        Answer resp = new Answer(tempQuestion.getID(), tempUser.getID(), milli, userAnswer);
                                        answer.create(resp);
                                        
                                        waitEnter();
                                    }break;
        
                                    case 2:{

                                      //#############################################################
                                      //####    ADICIONAR A OPÇÃO DE AVALIAR À PERGUNTA AQUI.    ####
                                      //#############################################################
                                      
                                    }break;
        
                                    case 3:{

                                      //###############################################################
                                      //####    ADICIONAR A OPÇÃO DE AVALIAR UMA RESPOSTA AQUI.    ####
                                      //###############################################################
                                      
                                    }break;

                                    default:
                                        System.out.println("Opcao invalida.");
                                        waitEnter();
                                }
                            } while (option3 != 0);
                        }
                    } break;
    
                    default:
                        System.out.println("Opcao invalida.");
                        waitEnter();
                }
            } while (option1 != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitEnter() {
        System.out.print("Aperte ENTER para continuar.");
        scanner.nextLine();
    }
    
    public static void main(String[] args) {
        // Variables
        int option, chances = 0;
        String name, email, password, question, answer, confirmCreation;

        try {
            CRUDUser user = new CRUDUser("pessoas");

            do {
                Clear.main();
                System.out.println("Smart Questions");
                System.out.println("===============\n");
                System.out.println("ACESSO\n");
                System.out.println("1) Acesso ao sistema");
                System.out.println("2) Novo usuario (primeiro acesso)");
                System.out.println("3) Listar usuarios");
                System.out.println("4) Excluir usuario");
                System.out.println("5) Esqueci minha senha (cadastrar uma nova senha)\n");
                System.out.println("0) Sair\n");
                System.out.print("Opcao: ");

                try {
                    option = Integer.valueOf(scanner.nextLine());
                } catch (NumberFormatException e) {
                    option = -1;
                }

                switch (option) {
                    case 0: {
                        System.out.println("\nObrigado por utilizar o nosso sitema. Saindo...");
                    } break;

                    case 1: {
                        Clear.main();
                        System.out.println("ACESSO AO SISTEMA\n");
                        System.out.print("Digite seu e-mail: ");

                        email = scanner.nextLine();

                        // E-mail isn't empty
                        if (email.length() != 0) {
                            ParEmailID parEmailID = user.search(email.hashCode());

                            if (parEmailID != null) {
                                User usuario = user.read(parEmailID.getID());
                                chances = 2;

                                while (chances >= 0) {
                                    System.out.print("Digite a sua senha: ");
                                    password = scanner.nextLine();

                                    if (password.hashCode() == usuario.getPassword()) {
                                        telaPrincipal(usuario);
                                        break;
                                    } else {
                                        System.out.println("\n-> Senha invalida, tente novamente. Chances restantes (" + chances + ").\n");
                                    }

                                    chances--;
                                }

                                if (chances == -1) {
                                    System.out.println("==============================================");
                                    System.out.println("Voce excedeu o limite permitido de tentativas.");
                                    System.out.println("==============================================\n");
                                    waitEnter();
                                }
                            } else {
                                System.out.println("\n=============================================================");
                                System.out.println("O e-mail (" + email + ") nao existe na nossa base de dados.");
                                System.out.println("=============================================================\n");
                                waitEnter();
                            }
                        } else {
                            System.out.println("\n================================");
                            System.out.println("Voce digitou um e-mail em branco.");
                            System.out.println("==================================\n");
                            waitEnter();
                        }
                    } break;

                    case 2: {
                        Clear.main();
                        System.out.println("Novo Usuario\n");
                        System.out.print("Digite seu E-mail: ");
                        
                        email = scanner.nextLine();

                        // E-mail isn't empty
                        if (email.length() != 0) {
                            ParEmailID parEmailID = user.search(email.hashCode());

                            if (parEmailID == null) {
                                System.out.print("Digite seu nome: ");
                                name = scanner.nextLine();
                                System.out.print("Digite sua senha: ");
                                password = scanner.nextLine();
                                System.out.print("Digite uma pergunta chave: ");
                                question = scanner.nextLine();
                                System.out.print("Digite uma resposta chave: ");
                                answer = scanner.nextLine();

                                Clear.main();
                                System.out.println("Dados inseridos");
                                System.out.println("===============\n");
                                System.out.println("Nome -> " + name);
                                System.out.println("Email -> " + email);
                                System.out.println("Senha -> " + password);
                                System.out.println("Pergunta -> " + question);
                                System.out.println("Resposta -> " + answer + "\n");
                                System.out.print("Deseja confirmar a criacao deste novo usuario Sim | Nao ? ");
                                confirmCreation = scanner.nextLine();

                                if (confirmCreation.toLowerCase().equals("sim")) {
                                    User usuario = new User(name, email, password, question, answer);
                                    user.create(usuario);

                                    System.out.println("\n===========================");
                                    System.out.println("Usuario criado com sucesso.");
                                    System.out.println("===========================\n");
                                    waitEnter();
                                } else {
                                    System.out.println("\n=========================================");
                                    System.out.println("Voce nao confirmou a criacao do usuario.");
                                    System.out.println("=========================================\n");
                                    waitEnter();
                                }
                            } else {
                                System.out.println("\n===========================================");
                                System.out.println("O e-mail ja existe na nossa base de dados.");
                                System.out.println("===========================================\n");
                                waitEnter();
                            }
                        } else {
                            System.out.println("\n==================================");
                            System.out.println("Voce digitou um e-mail em branco.");
                            System.out.println("==================================\n");
                            waitEnter();
                        }
                    } break;

                    case 3: {
                        Clear.main();
                        
                        user.showUsers();

                        waitEnter();
                    } break;

                    case 4: {
                        Clear.main();
                        System.out.println("Excluir usuario\n");
                        System.out.print("Digite seu e-mail: ");

                        email = scanner.nextLine();

                        // E-mail isn't empty
                        if (email.length() != 0) {
                            ParEmailID parEmailID = user.search(email.hashCode());

                            if (parEmailID != null) {
                                User usuario = user.read(parEmailID.getID());
                                chances = 2;

                                while (chances >= 0) {
                                    System.out.print("Digite a sua senha: ");
                                    password = scanner.nextLine();

                                    if (password.hashCode() == usuario.getPassword()) {
                                        System.out.print("Voce realmente deseja excluir a sua conta Sim | Nao ? ");
                                        confirmCreation = scanner.nextLine();

                                        if (confirmCreation.toLowerCase().equals("sim")) {
                                            user.delete(usuario);

                                            System.out.println("\n===========================");
                                            System.out.println("Usuario excluido com sucesso.");
                                            System.out.println("===========================\n");
                                            waitEnter();
                                        } else {
                                            System.out.println("\n=========================================");
                                            System.out.println("Voce nao confirmou a exclusao do usuario.");
                                            System.out.println("=========================================\n");
                                            waitEnter();
                                        }
                                        break;
                                    } else {
                                        System.out.println("\n-> Senha invalida, tente novamente. Chances restantes (" + chances + ").\n");
                                    }

                                    chances--;
                                }

                                if (chances == -1) {
                                    System.out.println("==============================================");
                                    System.out.println("Voce excedeu o limite permitido de tentativas.");
                                    System.out.println("==============================================\n");
                                    waitEnter();
                                }
                            } else {
                                System.out.println("\n=============================================================");
                                System.out.println("O e-mail (" + email + ") nao existe na nossa base de dados.");
                                System.out.println("=============================================================\n");
                                waitEnter();
                            }
                        } else {
                            System.out.println("\n================================");
                            System.out.println("Voce digitou um e-mail em branco.");
                            System.out.println("==================================\n");
                            waitEnter();
                        }
                    } break;

                    case 5: {
                        System.out.println("\nCadastrar Nova Senha\n");
                        System.out.print("Digite seu E-mail: ");

                        email = scanner.nextLine();

                        if (email.length() != 0) {
                            ParEmailID parEmailID = user.search(email.hashCode());

                            if (parEmailID != null) {
                                User usuario = user.read(parEmailID.getID());
                                System.out.println("\n====================================================");
                                System.out.println("Responda a seguinte pergunta (somente uma tentativa).");
                                System.out.println(usuario.getQuestion());
                                answer = scanner.nextLine();

                                if (answer.equals(usuario.getAnswer())) {
                                    System.out.println("Resposta correta.");
                                    System.out.print("Digite uma nova senha: ");
                                    boolean passwordIsTheSame = true;
                                    do {
                                        password = scanner.nextLine();
                                    
                                        if(password.hashCode() == usuario.getPassword()) {
                                            System.out.println("\nA nova senha nao pode ser igual a anterior.");
                                            System.out.print("Digite uma senha diferente: ");
                                        } else {
                                            usuario.setPassword(password);
                                            System.out.println("\nCadastro de nova senha efetuado com sucesso.\n");
                                            passwordIsTheSame = false;
                                            user.update(usuario);
                                            waitEnter();
                                        }
                                    } while (passwordIsTheSame);
                                } else {
                                    System.out.println("\n=========================================================");
                                    System.out.println("Resposta incorreta. Encerrando o cadastro de nova senha...");
                                    System.out.println("===========================================================\n");
                                    waitEnter();
                                }
                            } else {
                                System.out.println("\n=============================================================");
                                System.out.println("O e-mail (" + email + ") nao existe na nossa base de dados.");
                                System.out.println("=============================================================\n");
                                waitEnter();
                            }
                        } else {
                            System.out.println("\n================================");
                            System.out.println("Voce digitou um e-mail em branco.");
                            System.out.println("==================================\n");
                            waitEnter();
                        }
                    } break;

                    default:
                        System.out.println("Opcao invalida.");
                        waitEnter();
                }
            } while (option != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
