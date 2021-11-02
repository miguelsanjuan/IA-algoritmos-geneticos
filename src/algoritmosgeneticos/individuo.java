package algoritmosgeneticos;

public class individuo {
    private int[] cromosoma;
    private float costo;
    private int sumainv;
    
    public individuo(){}

    public individuo(int[] cromosoma){
            this.cromosoma = cromosoma;
    }

    public void setCromosoma(int[] cromosoma){
            this.cromosoma = cromosoma;
    }

    public int[] getCromosoma(){
            return this.cromosoma;
    }

    public float getCosto(){
            return this.costo;
    }
    
    public int getSuma(){
            return this.sumainv;
    }

    public void ObtenerCosto(){
            this.costo = 0.0f;
            this.costo = (2f * cromosoma[0]) 
                       + (2.4f * cromosoma[1]) 
                       + (3f * cromosoma[2]) 
                       + (4f * cromosoma[3] );
                

            ValidarRest();
    }

    //calcula aptitud
    public void ValidarRest(){
        int Cont = 0;

        for (int i = 0; i < this.cromosoma.length; i++)
        {
            Cont += this.cromosoma[i];
        }
        
        
       
        
        //establecer valores de inversiones 
        
        double[] aptitud = new double[4];
        
        switch (this.cromosoma[0]) 
        {
            case 0:  aptitud[0] = 0;
                     break;
            case 1:  aptitud[0] = 0.28;
                     break;
            case 2:  aptitud[0] = 0.45;
                     break;
            case 3:  aptitud[0] = 0.65;
                     break;
            case 4:  aptitud[0] = 0.78;
                     break;
            case 5:  aptitud[0] = 0.90;
                     break;
            case 6:  aptitud[0] = 1.02;
                     break;
            case 7:  aptitud[0] = 1.13;
                     break;
            case 8:  aptitud[0] = 1.23;
                     break;
            case 9:  aptitud[0] = 1.32;
                     break;
            case 10: aptitud[0] = 1.38;
                     break;
            default: aptitud[0] = 0;
                     break;
        }
        
        switch (this.cromosoma[1]) 
        {
            case 0:  aptitud[1] = 0;
                     break;
            case 1:  aptitud[1] = 0.25;
                     break;
            case 2:  aptitud[1] = 0.41;
                     break;
            case 3:  aptitud[1] = 0.55;
                     break;
            case 4:  aptitud[1] = 0.65;
                     break;
            case 5:  aptitud[1] = 0.75;
                     break;
            case 6:  aptitud[1] = 0.80;
                     break;
            case 7:  aptitud[1] = 0.85;
                     break;
            case 8:  aptitud[1] = 0.88;
                     break;
            case 9:  aptitud[1] = 0.90;
                     break;
            case 10: aptitud[1] = 0.90;
                     break;
            default: aptitud[1] = 0;
                     break;
        }
        
        switch (this.cromosoma[2]) 
        {
            case 0:  aptitud[2] = 0;
                     break;
            case 1:  aptitud[2] = 0.15;
                     break;
            case 2:  aptitud[2] = 0.25;
                     break;
            case 3:  aptitud[2] = 0.40;
                     break;
            case 4:  aptitud[2] = 0.50;
                     break;
            case 5:  aptitud[2] = 0.62;
                     break;
            case 6:  aptitud[2] = 0.73;
                     break;
            case 7:  aptitud[2] = 0.82;
                     break;
            case 8:  aptitud[2] = 0.90;
                     break;
            case 9:  aptitud[2] = 0.96;
                     break;
            case 10: aptitud[2] = 1.00;
                     break;
            default: aptitud[2] = 0;
                     break;
        }
        switch (this.cromosoma[3]) 
        {
            case 0:  aptitud[3] = 0;
                     break;
            case 1:  aptitud[3] = 0.20;
                     break;
            case 2:  aptitud[3] = 0.33;
                     break;
            case 3:  aptitud[3] = 0.42;
                     break;
            case 4:  aptitud[3] = 0.48;
                     break;
            case 5:  aptitud[3] = 0.53;
                     break;
            case 6:  aptitud[3] = 0.56;
                     break;
            case 7:  aptitud[3] = 0.58;
                     break;
            case 8:  aptitud[3] = 0.60;
                     break;
            case 9:  aptitud[3] = 0.60;
                     break;
            case 10: aptitud[3] = 0.60;
                     break;
            default: aptitud[3] = 0;
                     break;
        }
            //se calcula la aptitud
            this.sumainv = this.cromosoma[0]+this.cromosoma[1] + this.cromosoma[2]+ this.cromosoma[3];
            int suma = Math.abs((this.cromosoma[0]+this.cromosoma[1] + this.cromosoma[2]+ this.cromosoma[3])-10);
            this.costo = ((float) (aptitud[0]+aptitud[1]+aptitud[2]+aptitud[3]))/((500*suma)+1);
        
        
        
    }
}
