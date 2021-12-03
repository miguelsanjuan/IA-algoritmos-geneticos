package algoritmosgeneticos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AlgoritmosGeneticos extends JFrame implements ActionListener{

    public Random random = new Random();
    public ArrayList<individuo> poblacion = new ArrayList<individuo>();
    public float[] Individuos;
    public int porcentajeMutacion = 80;
    public int TamañoPoblacion = 0;
    public int porcentajeCruza = 0;
    public int tipoCruza = 0;
    public int iteraciones = 0;
    public int tipoSeleccion = 0;
    public int mutacion = 0;
    public int columnasCosto = 4;
    public String[][] PoblacionInicial;
    public String[][] PoblacionFinal;
    public JButton btnAceptar;
    public JButton btnLimpiar;
    public JTextField JTFMejorIndividuo;
    public JTextField JTFMejorCosto;
    public JTextField JTFPoblacion;
    public JTextField JTFIteraciones;
    public JTextField JTFPCruza;
    public JTextField JTFPMutacion;
    public JTable JTablePoblacionIni;
    public JTable JTablePoblacionFin;
    public String[] columns = new String[] {"Individuo", "C1", "C2", "C3", "C4", "Aptitud"};
    public String[][] rows = new String [][]{{"","","","","",""}};
    public ChartPanel CPanel;
    public JFreeChart JFCGrafica;
    public JComboBox JCBMutacion;
    public JComboBox JCBSeleccion;
    public JComboBox JCBCruza;

    public void CrearVista(){
        this.setTitle("Proyecto AG Inteligencia Artificial");
        this.setSize(1210, 710);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

       

        //POBLACION INICIAL
        Border blacklineP = BorderFactory.createTitledBorder("Poblaciones");
        JPanel JPanelPoblaciones = new JPanel();
        JPanelPoblaciones.setBorder(blacklineP);
        JPanelPoblaciones.setSize(530,560);
        JPanelPoblaciones.setLayout(null);
        Color colorB=new Color(169, 204, 227);
        JPanelPoblaciones.setBackground(colorB);
        this.add(JPanelPoblaciones);
        JPanelPoblaciones.setLocation(5, 105);

        JTablePoblacionIni = new JTable(rows,columns);
        JTablePoblacionIni.setSize(200, 200);
        Border blacklinePi = BorderFactory.createTitledBorder("Poblacion Inicial");
        JScrollPane JSPPoblacionInicial = new JScrollPane(JTablePoblacionIni);
        JSPPoblacionInicial.setBorder(blacklinePi);
        JPanelPoblaciones.add(JSPPoblacionInicial);
        JSPPoblacionInicial.setSize(510, 245);
        JSPPoblacionInicial.setLocation(10, 20);

        JTablePoblacionFin = new JTable(rows, columns);
        JTablePoblacionFin.setSize(200, 200);
        Border blacklinePf = BorderFactory.createTitledBorder("Poblacion Final");
        JScrollPane JSPPoblacionFin = new JScrollPane(JTablePoblacionFin);
        JSPPoblacionFin.setBorder(blacklinePf);
        JPanelPoblaciones.add(JSPPoblacionFin);
        JSPPoblacionFin.setSize(510, 245);
        JSPPoblacionFin.setLocation(10, 275);

        JLabel JLMejorIndividuo = new JLabel("Mejor Individuo: ",SwingConstants.RIGHT);
        JPanelPoblaciones.add(JLMejorIndividuo);
        JLMejorIndividuo.setSize(110, 25);
        JLMejorIndividuo.setLocation(10, 525);

        JTFMejorIndividuo = new JTextField();
        JPanelPoblaciones.add(JTFMejorIndividuo);
        JTFMejorIndividuo.setSize(90, 25);
        JTFMejorIndividuo.setLocation(125, 525);
        JTFMejorIndividuo.setEditable(false);

        JLabel JLMejorCosto = new JLabel("Mejor Costo: ",SwingConstants.RIGHT);
        JPanelPoblaciones.add(JLMejorCosto);
        JLMejorCosto.setSize(110, 25);
        JLMejorCosto.setLocation(200, 525);

        JTFMejorCosto = new JTextField();
        JPanelPoblaciones.add(JTFMejorCosto);
        JTFMejorCosto.setSize(90, 25);
        JTFMejorCosto.setLocation(350, 525);
        JTFMejorCosto.setEditable(false);

        //POBLACION FINAL
        JPanel JPanelConf = new JPanel();
        JPanelConf.setLayout(null);
        JPanelConf.setBackground(colorB);
        JPanelConf.setSize(530,100);
        this.add(JPanelConf);
        JPanelConf.setLocation(5, 5);

        JLabel JLTamano = new JLabel("No.Población: ",SwingConstants.RIGHT);
        JTFPoblacion =  new JTextField();
        JPanelConf.add(JLTamano);
        JPanelConf.add(JTFPoblacion);
        JTFPoblacion.setSize(80, 20);
        JTFPoblacion.setLocation(110, 20);
        JLTamano.setSize(90,20);
        JLTamano.setLocation(15, 20);

        JLabel JLIteraciones = new JLabel("No.Iteraciónes: ",SwingConstants.RIGHT);
        JTFIteraciones =  new JTextField();
        JPanelConf.add(JTFIteraciones);
        JTFIteraciones.setSize(80, 20);
        JTFIteraciones.setLocation(110, 45);
        JPanelConf.add(JLIteraciones);
        JLIteraciones.setSize(90,20);
        JLIteraciones.setLocation(15, 45);

        


        JLabel JLPCruza = new JLabel("Cruza %: ",SwingConstants.RIGHT);
        JTFPCruza =  new JTextField();
        JPanelConf.add(JTFPCruza);
        JTFPCruza.setSize(80, 20);
        JTFPCruza.setLocation(270, 45);
        JPanelConf.add(JLPCruza);
        JLPCruza.setSize(70,20);
        JLPCruza.setLocation(190, 45);

    

        JLabel JLPMutacion = new JLabel("Mutación %: ",SwingConstants.RIGHT);
        JTFPMutacion = new JTextField();
        JPanelConf.add(JTFPMutacion);
        JTFPMutacion.setSize(80, 20);
        JTFPMutacion.setLocation(270, 20);
        JPanelConf.add(JLPMutacion);
        JLPMutacion.setSize(90,20);
        JLPMutacion.setLocation(180, 20);
        
        btnAceptar = new JButton("Aceptar");
        JPanelConf.add(btnAceptar);
        btnAceptar.setSize(90, 20);
        btnAceptar.setLocation(380, 20);
        btnAceptar.addActionListener(this);

        btnLimpiar = new JButton("Limpiar");
        JPanelConf.add(btnLimpiar);
        btnLimpiar.setSize(90, 20);
        btnLimpiar.setLocation(380, 47);
        btnLimpiar.addActionListener(this);

        JFCGrafica = ChartFactory.createLineChart(null,"Generacion", "Beneficio", null,PlotOrientation.VERTICAL, true, true, false);
        CPanel = new ChartPanel(JFCGrafica);

        CPanel.setSize(650, 660);
        JFCGrafica.setBackgroundPaint(colorB);

        this.add(CPanel);
        CPanel.setLocation(540, 5);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    public AlgoritmosGeneticos(){
        CrearVista();
    }
    
    public static void main(String[] args){
            new AlgoritmosGeneticos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        
        if(btn == btnAceptar){

            if(JTFPoblacion.getText().length() > 0 && 
                JTFIteraciones.getText().length() > 0 &&
                            JTFPCruza.getText().length() > 0 &&
                                JTFPMutacion.getText().length() > 0){
                
                TamañoPoblacion = Integer.parseInt(JTFPoblacion.getText());
                iteraciones = Integer.parseInt(JTFIteraciones.getText());
                tipoSeleccion = 0;
                tipoCruza = 0;
                porcentajeCruza = Integer.parseInt(JTFPCruza.getText());
                mutacion = 0;
                porcentajeMutacion = Integer.parseInt(JTFPMutacion.getText());
                

                Individuos = new float[iteraciones];

                CrearPoblacion(poblacion);
                PoblacionInicial = ObtenerPoblacion(poblacion);

                for (int i = 0; i < iteraciones; i++){

                        if(tipoSeleccion == 1) {
                            TipoSelTorneo(poblacion);
                        }

                        if(tipoCruza == 0){
                            RCruza(poblacion, tipoCruza);
                        }

                        if(mutacion == 0){
                            mutacion(poblacion);
                        }

                        Individuos[i] = mejorIndividuo();
                        
                        
                }

                PoblacionFinal = ObtenerPoblacion(poblacion);
                float res = mejorIndividuo();
                System.out.print(res);
                
                String psum = mejorCosto();
                
                
                JTFMejorIndividuo.setText(res+"");
                
                JTFMejorCosto.setText(psum+"");
                
                this.remove(CPanel);
                this.repaint();
                this.revalidate();
                
                Object[][] dataInicial = PoblacionInicial;
                Object[][] dataFinal = PoblacionFinal;

                DefaultTableModel modelInicial = new DefaultTableModel(dataInicial,columns);
                DefaultTableModel modelFinal = new DefaultTableModel(dataFinal,columns);

                JTablePoblacionIni.setModel(modelInicial);
                JTablePoblacionFin.setModel(modelFinal);
                
                DefaultCategoryDataset datos = new DefaultCategoryDataset();

                for (int i = 0; i < Individuos.length; i++){
                    datos.addValue(Individuos[i], "Individuo", (""+(i+1)));
                }

                JFCGrafica = ChartFactory.createLineChart(null,"Generacion", "Beneficio", datos,PlotOrientation.VERTICAL, true, true, false);
                Color colorB=new Color(169, 204, 227);
                CPanel.setBackground(colorB);
                JFCGrafica.setBackgroundPaint(colorB);
                CPanel = new ChartPanel(JFCGrafica);

                CPanel.setSize(650, 660);
                
                

                this.add(CPanel);
                CPanel.setLocation(540, 5);
                this.repaint();
                this.revalidate();
            }
        }else if(btn == btnLimpiar){
            JTFIteraciones.setText("");
            //JCBSeleccion.setSelectedIndex(0);
            //JCBSeleccion.setText("");
            JTFPoblacion.setText("");
            JTFPMutacion.setText("");
            //JCBMutacion.setSelectedIndex(0);
            JTFPCruza.setText("");
            //JCBCruza.setSelectedIndex(0);
            JTFMejorIndividuo.setText("");
            
            DefaultTableModel modelInicial = new DefaultTableModel(rows,columns);
            DefaultTableModel modelFinal = new DefaultTableModel(rows,columns);
            
            JTablePoblacionIni.setModel(modelInicial);
            JTablePoblacionFin.setModel(modelFinal);
        }
    }
    
    public String[][] ObtenerPoblacion(ArrayList<individuo> poblacion){
        String[][] poblacionAux = new String[TamañoPoblacion][6];

        for (int i = 0; i < TamañoPoblacion; i++){
            poblacionAux[i][0] = (i+1)+"";
            for (int j = 0; j < 4; j++){
                poblacionAux[i][j+1] = poblacion.get(i).getCromosoma()[j]+"";
            }
            poblacionAux[i][5] = poblacion.get(i).getCosto()+"";
        }
        
        return poblacionAux;
    }

    public void TipoSelTorneo(ArrayList<individuo> poblacion){
        ArrayList<individuo> PoblacionAux = new ArrayList<individuo>();
        int individuo1 = 0, individuo2 = 0;
        Collections.shuffle(poblacion);

        for (int i = 0; i < TamañoPoblacion; i++){
            individuo1 = random.nextInt(TamañoPoblacion);
            do{ individuo2 = random.nextInt(TamañoPoblacion); }while(individuo1 == individuo2);
            PoblacionAux.add((poblacion.get(individuo1).getCosto() < poblacion.get(individuo2).getCosto()) ? poblacion.get(individuo1) : poblacion.get(individuo2));
        }

        this.poblacion = new ArrayList<>(PoblacionAux);	
    }

    public void CrearPoblacion(ArrayList<individuo> poblacion){	
        
        for (int i = 0; i < TamañoPoblacion; i++){
            poblacion.add(new individuo(Semilla()));
            poblacion.get(i).ObtenerCosto();
        }	
    }
   //se generan numeros aletorios para costo del 1 al 10
    public int[] Semilla(){
        int[] arrResultados = new int[columnasCosto];
        int nRandom = -1;

        for (int i = 0; i < arrResultados.length; i++){
            nRandom = (int) Math.round(random.nextInt(10));
            arrResultados[i] = nRandom;
        }

        return arrResultados;
    }
    
    

    public float mejorIndividuo(){
        float MejorIndividuo = 0.0f;
        float mejoractual = 0.0f;
        int conind=0;
        for (individuo ind: this.poblacion){
            
            if(ind.getSuma() == 10) {
                conind++;
                 
              if (conind < 2){
                MejorIndividuo = ind.getCosto();
                mejoractual=MejorIndividuo;
                //ind.getCromosoma()
                
                  
                }else
                    if(conind > 1){
                       //genera nuevo mejor individuo y compara con el mejor individuo anterior
                       //para elejir el mayor
                       float nuevomejor = ind.getCosto();
                        
                        if(nuevomejor > MejorIndividuo){
                            MejorIndividuo = ind.getCosto();
                            mejoractual=MejorIndividuo;
                        }else{
                      //MejorIndividuo = ind.getCosto();
                        }
                  
                    }
              
            }
            if(MejorIndividuo == 0.0f) {
             // si la suma no es 10
             //MejorIndividuo = ind.getCosto();
            //MejorIndividuo = mejoractual;
            //MejorIndividuo = 1.81f;
            }    
        }

        return MejorIndividuo;
    }
    
    public String mejorCosto(){
        String mejorCosto = "";
        float MejorIndividuo = 0.0f;
        int conind=0;
        for (individuo ind: this.poblacion){
            //mejorCosto = ind.getSuma();
            if(ind.getSuma() == 10) {
            
            
            conind++;
                
                 
              if (conind < 2){
                MejorIndividuo = ind.getCosto();
                mejorCosto = ind.getBeneficio();
                //ind.getCromosoma()
                
                  
                }else
                    if(conind > 1){
                       float nuevomejor = ind.getCosto();
                        
                        if(nuevomejor > MejorIndividuo){
                            MejorIndividuo = ind.getCosto();
                            mejorCosto = ind.getBeneficio();
                        }else{
                      //MejorIndividuo = ind.getCosto();
                        }
                  
                    }
            
            }
            
        }
        return mejorCosto;
        
    }

    

    public individuo realizarCruza(int tipoCruza, individuo individuo1, individuo individuo2, int PuntoDeCruza, int PuntoDeCruza2, int varHijo){
        int[] CromosomaAuxiliar = new int[individuo1.getCromosoma().length];
        individuo individuoAuxiliar;

        if(tipoCruza == 0){
            if(varHijo == 1){
                System.arraycopy(individuo1.getCromosoma(), 0, CromosomaAuxiliar, 0, individuo1.getCromosoma().length);
                System.arraycopy(individuo2.getCromosoma(), PuntoDeCruza, CromosomaAuxiliar, PuntoDeCruza, (individuo2.getCromosoma().length-PuntoDeCruza));	
            }else{
                System.arraycopy(individuo2.getCromosoma(), 0, CromosomaAuxiliar, 0, individuo2.getCromosoma().length);
                System.arraycopy(individuo1.getCromosoma(), PuntoDeCruza, CromosomaAuxiliar, PuntoDeCruza, (individuo1.getCromosoma().length-PuntoDeCruza));
            }
        }else{
            if(varHijo == 1){
                System.arraycopy(individuo1.getCromosoma(), 0, CromosomaAuxiliar, 0, individuo1.getCromosoma().length);
                System.arraycopy(individuo2.getCromosoma(), PuntoDeCruza, CromosomaAuxiliar, PuntoDeCruza, ((PuntoDeCruza2-PuntoDeCruza)+1));
                System.arraycopy(individuo1.getCromosoma(), PuntoDeCruza2, CromosomaAuxiliar, PuntoDeCruza, (individuo1.getCromosoma().length-PuntoDeCruza2));
            }else{
                System.arraycopy(individuo2.getCromosoma(), 0, CromosomaAuxiliar, 0, individuo2.getCromosoma().length);
                System.arraycopy(individuo1.getCromosoma(), PuntoDeCruza, CromosomaAuxiliar, PuntoDeCruza, ((PuntoDeCruza2-PuntoDeCruza)+1));
                System.arraycopy(individuo2.getCromosoma(), PuntoDeCruza2, CromosomaAuxiliar, PuntoDeCruza, (individuo1.getCromosoma().length-PuntoDeCruza2));
            }
        }

        individuoAuxiliar = new individuo(CromosomaAuxiliar);
        individuoAuxiliar.ObtenerCosto();
        return individuoAuxiliar;
    }

    public void mutacion(ArrayList<individuo> poblacion){
        int nMutaciones = (TamañoPoblacion * porcentajeMutacion/100), PosicionR = 0;
        ArrayList<individuo> PoblacionAuxiliar = new ArrayList<individuo>(poblacion);
        int PosicionM[] = new int[nMutaciones];

        for (int i = 0; i < nMutaciones; i++){
            PosicionM[i] = -1;
            do{ PosicionR = random.nextInt(TamañoPoblacion); }while(ExistePosicion(PosicionM, PosicionR));
            PosicionM[i] = PosicionR;
        }

        for (int i = 0; i < PosicionM.length; i++){	
            PoblacionAuxiliar.set(PosicionM[i], mutacionNormal(poblacion.get(PosicionM[i])));
        }
        
        this.poblacion = new ArrayList<>(PoblacionAuxiliar);
    }
    
    public individuo mutacionNormal(individuo individuo1){
        int[] CromosomaAuxiliar = new int[individuo1.getCromosoma().length];
        System.arraycopy(individuo1.getCromosoma(), 0, CromosomaAuxiliar, 0, individuo1.getCromosoma().length);
        individuo individuoAux;
        int Auxiliar, nPosicion;

        nPosicion = random.nextInt(columnasCosto);
        Auxiliar = CromosomaAuxiliar[nPosicion];
        Auxiliar = (Auxiliar == 1) ? 0 : 1;
        CromosomaAuxiliar[nPosicion] = Auxiliar;
        individuoAux = new individuo(CromosomaAuxiliar);
        individuoAux.ObtenerCosto();

        return individuoAux;
    }
    
    public void RCruza(ArrayList<individuo> poblacion, int tipoCruza){
        ArrayList<individuo> PoblacionAuxiliar = new ArrayList<individuo>(poblacion);
        int numeroCruzas = (TamañoPoblacion * porcentajeCruza/100), varPosicionR = 0, puntoCruza, puntoCruza1, puntoCruza2, varAuxiliar;
        int arrPosicionCruza[] = new int[numeroCruzas/2];
        individuo individuo1, individuo2;

        for (int i = 0; i < numeroCruzas/2; i++){
            arrPosicionCruza[i] = -1;
            do{
                varPosicionR = random.nextInt(TamañoPoblacion - 2);
                if(varPosicionR % 2 != 0) varPosicionR++;
            }while(ExistePosicion(arrPosicionCruza, varPosicionR));
            arrPosicionCruza[i] = varPosicionR;
        }

        for (int i = 0; i < arrPosicionCruza.length; i++){			
            individuo1 = new individuo(PoblacionAuxiliar.get(arrPosicionCruza[i]).getCromosoma());
            individuo2 =  new individuo(PoblacionAuxiliar.get(arrPosicionCruza[i]+1).getCromosoma());

            if(tipoCruza == 0){
                //puntoCruza = random.nextInt(4);
                puntoCruza = random.nextInt(4)+1;
                PoblacionAuxiliar.set(arrPosicionCruza[i], realizarCruza(0,individuo1,individuo2, puntoCruza, 0, 1));
                PoblacionAuxiliar.set((arrPosicionCruza[i]+1), realizarCruza(0,individuo1,individuo2, puntoCruza, 0, 2));
            }
        }

        this.poblacion = new ArrayList<>(PoblacionAuxiliar);
    }

    public boolean ExistePosicion(int[] PosicionCruza, int Posicion){
        for (int i = 0; i< PosicionCruza.length; i++){
            if(PosicionCruza[i] == Posicion){
                return true;
            }
        }
        return false;
    }
}
