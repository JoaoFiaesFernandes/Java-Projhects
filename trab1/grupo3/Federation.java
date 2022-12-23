package trab1.grupo3;

public class Federation extends Union {

    public Federation(String nm) {
        super(nm, "Estado federal");
    }

    // o metodo retorna true
    public boolean isSovereign() {
        return true;
    }

    // o metodo append com um parâmetro caso o estado não seja um pais
    // autonomo lança um StateException, caso contrário chama o metodo append
    // da class base para o adicionar.
    // Retorna a propria Federation
    public Federation append(State s) throws StateException {
        if (s.isSovereign()) //s.isSovereign() == false
            throw new StateException(s);
        super.append(s);
        return this;
    }


    // o metodo append com dois parametros instancia um country autonomo com
    // o nome e a area recebidos por parametro e evoca o metodo append com um
    // parametro.
    // retorna a propria federation.
    // Este metodo não tem a clausula throws na assinatura

    public Federation append(String stName, int area) {
        State autonomo = new Country(stName, area, false);
        try {
            super.append(autonomo);
        } catch (StateException e) {
            throw new RuntimeException(e);
        }
        return this;
    }


    // o metodo greaterState retora o maior estado da federação ou null caso ainda
    // não tenham sido adicionados estados. Caso existam dois ou mais estados
    // iguais retorna o ultimo adicionado
    public State greaterState() {

        if (this.size() == 0) { //null caso ainda não tenham sido adicionados estados
            return null;
        }

        int bigger = 0;
        State s = null;

        for (State st : this) {
           if (bigger <= st.getArea()) {
               bigger = st.getArea();
           }
       }
        return s;
    }

}
