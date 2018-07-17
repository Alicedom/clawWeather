package claw.web.jsou.learnforfun;

public class Main {
    public static void main(String[] args) {
        Clone clone3 = new Clone();

        clone3.getData("http://www.learnforfun.vn/vi/course/446/quiz/31/question/",1,607, "model/jsou/learnforfun/question/");
        clone3.getData("http://www.learnforfun.vn/vi/node/",610,869, "model/jsou/learnforfun/node/");
    }
}
