/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Process;

import GUI.Algorithms;
import GUI.Techniques;

/**
 *
 * @author Gonzalo
 */
public class GlobalKnowledge {
    //public static short[][][][] Know=new short[3][5][4][3];//tipo de iagen,metodo,clusters maximos,colores
    
    static float NadhK[][]=new float[4][3];
    
    static float NadhFCM[][]=new float[4][3];
    static float NadhK3[][]=new float[4][3];
    static float NadhKM[][]=new float[4][3];
    static float NadhH[][]=new float[4][3];
    static float NadhAg[][]=new float[4][3];
    
    static boolean NadhFCMB=false;
    
    static boolean NadhKB=false;
    static boolean NadhK3B=false;
    static boolean NadhKMB=false;
    static boolean NadhHB=false;
    static boolean NadhAgB=false;
    
    static float NadhFCMVar[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    
    static float NadhKVar[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    static float NadhK3Var[][]={{65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f}};
    static float NadhKMVar[][]={{65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f}};
    static float NadhHVar[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    static float NadhAgVar[][]={{65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f},
                                       {65536f,65536f,65536f}};
    
    ///////////////////////////////////////////////
    
    static float AtpFCM[][]=new float[4][3];
    
    static float AtpK[][]=new float[4][3];
    static float AtpK3[][]=new float[4][3];
    static float AtpKM[][]=new float[4][3];
    static float AtpH[][]=new float[4][3];
    static float AtpAg[][]=new float[4][3];
    
    static boolean AtpFCMB=false;
    
    static boolean AtpKB=false;
    static boolean AtpK3B=false;
    static boolean AtpKMB=false;
    static boolean AtpHB=false;
    static boolean AtpAgB=false;
    
    static float AtpFCMVar[][]={{65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f}};
    
    static float AtpKVar[][]={{65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f}};
    static float AtpK3Var[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    static float AtpKMVar[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    static float AtpHVar[][]={{65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f},
                                     {65536f,65536f,65536f}};
    static float AtpAgVar[][]={{65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f},
                                      {65536f,65536f,65536f}};
    
    /////////////////////////////////////////////////////
    
    static float CoxFCM[][]=new float[4][3];
    
    static float CoxK[][]=new float[4][3];
    static float CoxK3[][]=new float[4][3];
    static float CoxKM[][]=new float[4][3];
    static float CoxH[][]=new float[4][3];
    static float CoxAg[][]=new float[4][3];
    
    static boolean CoxFCMB=false;
    
    static boolean CoxKB=false;
    static boolean CoxK3B=false;
    static boolean CoxKMB=false;
    static boolean CoxHB=false;
    static boolean CoxAgB=false;
    
    static float CoxFCMVar[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    
    
    static float CoxKVar[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    static float CoxK3Var[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    static float CoxKMVar[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    static float CoxHVar[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    static float CoxAgVar[][]={{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f},{65536f,65536f,65536f}};
    
    
    //public static float [][][][] var={{{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}}},{{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}}},{{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}},{{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f},{8100f,8100f,8100f}}}};
    //public static float [][][][] var=new float[3][5][4][3];
    
    //private static boolean[][] isAssigned={{false,false,false,false,false},{false,false,false,false,false},{false,false,false,false,false}};
    
   /*
    public static void setRed(float[] result,byte type,byte source){
        for(byte i=0;i<result.length;i++){
            Know[type][source][i][0]=(short)result[i];
        }
    }
    
    public static void setGreen(float[] result,byte type,byte source){
        for(byte i=0;i<result.length;i++){
            Know[type][source][i][1]=(short)result[i];
        }
    }
    
    public static void setBlue(float[] result,byte type,byte source){
        for(byte i=0;i<result.length;i++){
            Know[type][source][i][2]=(short)result[i];
        }
    }
    
    public static void SaveData(float[][] res,byte type,byte source){
        setRed(res[0],type,source);
        setGreen(res[1],type,source);
        setBlue(res[2],type,source);
        isAssigned[type][source]=true;
    }
    */
    
    
    
    public static void SaveFCM(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveFCMN(res);
            break;
            case 1:
                SaveFCMA(res);
            break;
            case 2:
                SaveFCMC(res);
            break;              
        }
    }
    
    static void SaveFCMN(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhFCM[i][j]=res[j][i];
            } 
        }
        NadhFCMB=true;
        
    }
    
    static void SaveFCMA(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpFCM[i][j]=res[j][i];
            } 
        }
        AtpFCMB=true;
    }
    
    static void SaveFCMC(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxFCM[i][j]=res[j][i];
            } 
        }
        CoxFCMB=true;
    }
    
    
    
    
    public static void SaveK(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveKN(res);
            break;
            case 1:
                SaveKA(res);
            break;
            case 2:
                SaveKC(res);
            break;              
        }
    }
    static void SaveKN(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhK[i][j]=res[j][i];
            } 
        }
        NadhKB=true;
        
    }
    
    static void SaveKA(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpK[i][j]=res[j][i];
            } 
        }
        AtpKB=true;
    }
    
    static void SaveKC(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxK[i][j]=res[j][i];
            } 
        }
        CoxKB=true;
    }
    
    
    
    
    public static void SaveK3(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveK3N(res);
            break;
            case 1:
                SaveK3A(res);
            break;
            case 2:
                SaveK3C(res);
            break;              
        }
    }
    
    static void SaveK3N(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhK3[i][j]=res[j][i];
            } 
        }
        NadhK3B=true;
    }
    
    static void SaveK3A(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpK3[i][j]=res[j][i];
            } 
        }
        AtpK3B=true;
    }
    
    static void SaveK3C(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxK3[i][j]=res[j][i];
            } 
        }
        CoxK3B=true;
    }
    
    
    
    public static void SaveKM(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveKMN(res);
            break;
            case 1:
                SaveKMA(res);
            break;
            case 2:
                SaveKMC(res);
            break;              
        }
    }
    
    static void SaveKMN(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhKM[i][j]=res[j][i];
            } 
        }
        NadhKMB=true;
    }
    
    static void SaveKMA(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpKM[i][j]=res[j][i];
            } 
        }
        AtpKMB=true;
    }
    
    static void SaveKMC(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxKM[i][j]=res[j][i];
            } 
        }
        CoxKMB=true;
    }
    
    
    
    
    public static void SaveH(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveHN(res);
            break;
            case 1:
                SaveHA(res);
            break;
            case 2:
                SaveHC(res);
            break;              
        }
    }
    
    static void SaveHN(float[][] res){
        System.out.println("\nHumano Nadh\n");
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhH[i][j]=res[j][i];
               System.out.print(NadhH[i][j]+"\t");
            }
           System.out.print("\n");
        }
        NadhHB=true;
    }
    
    static void SaveHNVar(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhHVar[i][j]=res[j][i];
               NadhKVar[i][j]=res[j][i];
               NadhK3Var[i][j]=res[j][i];
               NadhKMVar[i][j]=res[j][i];
               NadhAgVar[i][j]=res[j][i];
            } 
        }
        NadhHB=true;
    }
    
    static void SaveHA(float[][] res){
         System.out.println("\nHumano Atp\n");
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpH[i][j]=res[j][i];
                System.out.print(AtpH[i][j]+"\t");
           }
           System.out.print("\n"); 
        }
        AtpHB=true;
    }
    
    static void SaveHAVar(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpHVar[i][j]=res[j][i];
               AtpKVar[i][j]=res[j][i];
               AtpK3Var[i][j]=res[j][i];
               AtpKMVar[i][j]=res[j][i];
               AtpAgVar[i][j]=res[j][i];
            } 
        }
        AtpHB=true;
    }
    
    static void SaveHC(float[][] res){
         System.out.println("\nHumano Cox\n");
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxH[i][j]=res[j][i];
                System.out.print(CoxH[i][j]+"\t");
           }
           System.out.print("\n"); 
        }
        CoxHB=true;
    }
    
    static void SaveHCVar(float[][] res){
        for(short i=0;i<(res[0]).length;i++){
           for(short j=0;j<3;j++){
               CoxHVar[i][j]=res[j][i];
               CoxKVar[i][j]=res[j][i];
               CoxK3Var[i][j]=res[j][i];
               CoxKMVar[i][j]=res[j][i];
               CoxAgVar[i][j]=res[j][i];
            } 
        }
        CoxHB=true;
    }
    
    
    
    public static void SaveAg(float[][] res,byte type){
        switch (type) {
            case 0:
                SaveAgN(res);
            break;
            case 1:
                SaveAgA(res);
            break;
            case 2:
                SaveAgC(res);
            break;              
        }
    }
    
    static void SaveAgN(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               NadhAg[i][j]=res[j][i];
            } 
        }
        NadhAgB=true;
    }
    
    static void SaveAgA(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               AtpAg[i][j]=res[j][i];
            } 
        }
        AtpAgB=true;
    }
    
    static void SaveAgC(float[][] res){
        for(short i=0;i<res[0].length;i++){
           for(short j=0;j<3;j++){
               CoxAg[i][j]=res[j][i];
            } 
        }
        CoxAgB=true;
    }
    
    /*
    public static boolean isAssigned(byte type,byte source){
        return isAssigned[type][source];
    }
    */
    
    public static boolean isAssigned(Techniques type,Algorithms source){
        boolean ret=false;
        switch(type){
            case NADH:
                switch (source){
                    case FuzzyCMeans:
                        ret=NadhFCMB;
                    break;
                    case KMeans3D:
                        ret=NadhK3B;
                    break;
                    case KohonenMap:
                        ret=NadhKMB;
                    break;
                    case Expert:
                        ret=NadhHB;
                    break;
                    case Aggregated:
                        ret=NadhAgB;
                    break;             
                }
            break;
            case ATPASA:
                switch (source){
                    case FuzzyCMeans:
                        ret=AtpFCMB;
                    break;
                    case KMeans3D:
                        ret=AtpK3B;
                    break;
                    case KohonenMap:
                        ret=AtpKMB;
                    break;
                    case Expert:
                        ret=AtpHB;
                    break;
                    case Aggregated:
                        ret=AtpAgB;
                    break;
                }
            break;
            case COX:
                switch (source){
                    case FuzzyCMeans:
                        ret=CoxFCMB;
                    break;
                    case KMeans3D:
                        ret=CoxK3B;
                    break;
                    case KohonenMap:
                        ret=CoxKMB;
                    break;
                    case Expert:
                        ret=CoxHB;
                    break;
                    case Aggregated:
                        ret=CoxAgB;
                    break;
                }
            break;
        }
        return ret;
    }
    
    public static void SaveVar(float[][] res,byte imagetype){
        switch(imagetype){
            case 0:
                SaveHNVar(res);
            break;
            case 1:
                SaveHAVar(res);
            break;
            case 2:
                SaveHCVar(res);
            break;
        }
    }
    
    
    
    
    public static boolean isAssignedATP(){
        return  AtpK3B || AtpKMB || AtpHB || AtpAgB || AtpFCMB;
    }
    
    public static boolean isAssignedNADH(){
        return  NadhK3B || NadhKMB || NadhHB || NadhAgB || NadhFCMB;
    }
    
    public static boolean isAssignedCOX(){
        return  CoxK3B || CoxKMB || CoxHB || CoxAgB || CoxFCMB;
    }
    

    public static boolean isAssignedNADHFCM(){
        return NadhFCMB;
    }
    
    
    public static boolean isAssignedNADHK(){
        return NadhKB;
    }
    
    public static boolean isAssignedNADHK3(){
        return NadhK3B;
    }
    
    public static boolean isAssignedNADHKM(){
        return NadhKMB;
    }
    
    public static boolean isAssignedNADHH(){
        return NadhHB;
    }
    
    public static boolean isAssignedNADHAg(){
        return NadhAgB;
    }
    
    
    public static boolean isAssignedATPFCM(){
        return AtpFCMB;
    }
    
    
    public static boolean isAssignedATPK(){
        return AtpKB;
    }
    
    public static boolean isAssignedATPK3(){
        return AtpK3B;
    }
    
    public static boolean isAssignedATPKM(){
        return AtpKMB;
    }
    
    public static boolean isAssignedATPH(){
        return AtpHB;
    }
    
    public static boolean isAssignedATPAg(){
        return AtpAgB;
    }
    
    
    public static boolean isAssignedCOXFCM(){
        return CoxFCMB;
    }
    
    
    
    public static boolean isAssignedCOXK(){
        return CoxKB;
    }
    
    public static boolean isAssignedCOXK3(){
        return CoxK3B;
    }
    
    public static boolean isAssignedCOXKM(){
        return CoxKMB;
    }
    
    public static boolean isAssignedCOXH(){
        return CoxHB;
    }
    
    public static boolean isAssignedCOXAg(){
        return CoxAgB;
    }
    
    public static boolean isAssignedFCM(Techniques tech){
        boolean ret=false;
        switch(tech){
            case NADH:
                ret = isAssignedNADHFCM();
            break;
            case ATPASA:
                ret = isAssignedATPFCM();
            break;
            case COX:
                ret = isAssignedCOXFCM();
            break;
        }
        return ret;
    }
    
    public static boolean isAssignedKM(Techniques tech){
        boolean ret=false;
        switch(tech){
            case NADH:
                ret = isAssignedNADHKM();
            break;
            case ATPASA:
                ret = isAssignedATPKM();
            break;
            case COX:
                ret = isAssignedCOXKM();
            break;
        }
        return ret;
    }
    
    public static boolean isAssignedK3(Techniques tech){
        boolean ret=false;
        switch(tech){
            case NADH:
                ret = isAssignedNADHK3();
            break;
            case ATPASA:
                ret = isAssignedATPK3();
            break;
            case COX:
                ret = isAssignedCOXK3();
            break;
        }
        return ret;
    }
    
    public static boolean isAssignedH(Techniques tech){
        boolean ret=false;
        switch(tech){
            case NADH:
                ret = isAssignedNADHH();
            break;
            case ATPASA:
                ret = isAssignedATPH();
            break;
            case COX:
                ret = isAssignedCOXH();
            break;
        }
        return ret;
    }
    
    public static boolean isAssignedAg(Techniques tech){
        boolean ret=false;
        switch(tech){
            case NADH:
                ret = isAssignedNADHAg();
            break;
            case ATPASA:
                ret = isAssignedATPAg();
            break;
            case COX:
                ret = isAssignedCOXAg();
            break;
        }
        return ret;
    }
    
    public static short getRed(Techniques tipo,Algorithms alg, int cluster){
        return (short)getColor(tipo,alg,cluster,0);
    }
    
    public static float getRedf(Techniques tipo,Algorithms alg, int cluster){
        return getColor(tipo,alg,cluster,0);
    }
    
    public static float getRedFCM(int tipo,int cluster){
        float resp=255;
        switch (tipo){
            case 0:
                resp=NadhFCM[cluster][0];
            break;
            case 1:
                resp=AtpFCM[cluster][0];
            break;
            case 2:
                resp=CoxFCM[cluster][0];
            break;
        }
        return resp;
    }
    public static float getRedD(Techniques tipo,Algorithms alg, int cluster){
        return (float)Math.sqrt(getColorVar(tipo,alg,cluster,0));
    }
    
    public static float getGreenf(Techniques tipo,Algorithms alg, int cluster){
        return getColor(tipo,alg,cluster,1);
    }
    
    public static short getGreen(Techniques tipo,Algorithms alg, int cluster){
        return (short)getColor(tipo,alg,cluster,1);
    }
    
     public static float getGreenFCM(int tipo, int cluster){
        float resp=255;
        switch (tipo){
            case 0:
                resp=NadhFCM[cluster][1];
            break;
            case 1:
                resp=AtpFCM[cluster][1];
            break;
            case 2:
                resp=CoxFCM[cluster][1];
            break;
        }
        return resp;
    }
     
    
    public static float getGreenD(Techniques tipo,Algorithms alg, int cluster){
        return (float)Math.sqrt(getColorVar(tipo,alg,cluster,1));
    }
    public static float getBluef(Techniques tipo,Algorithms alg, int cluster){
        return getColor(tipo,alg,cluster,2);
    }
    
    public static short getBlue(Techniques tipo,Algorithms alg, int cluster){
        return (short)getColor(tipo,alg,cluster,2);
    }
    
    public static float getBlueFCM(int tipo, int cluster){
        float resp=255;
        switch (tipo){
            case 0:
                resp=NadhFCM[cluster][2];
            break;
            case 1:
                resp=AtpFCM[cluster][2];
            break;
            case 2:
                resp=CoxFCM[cluster][2];
            break;
        }
        return resp;
    }
    
    public static float getBlueD(Techniques tipo,Algorithms alg, int cluster){
        return (float)Math.sqrt(getColorVar(tipo,alg,cluster,2));
    }
    
    static float getColor(Techniques tipo,Algorithms alg, int cluster,int col){
        float resp=255f;
        switch (tipo){
            case NADH:
                switch (alg){
                    case FuzzyCMeans:
                        resp=NadhFCM[cluster][col];
                    break;
                    case KMeans3D:
                        resp=NadhK3[cluster][col];
                    break;
                    case KohonenMap:
                        resp=NadhKM[cluster][col];
                    break;
                    case Expert:
                        resp=NadhH[cluster][col];
                    break;
                    case Aggregated:
                        resp=NadhAg[cluster][col];
                    break;
                }
            break;
            case ATPASA:
                switch (alg){
                    case FuzzyCMeans:
                        resp=AtpFCM[cluster][col];
                    break;
                    case KMeans3D:
                        resp=AtpK3[cluster][col];
                    break;
                    case KohonenMap:
                        resp=AtpKM[cluster][col];
                    break;
                    case Expert:
                        resp=AtpH[cluster][col];
                    break;
                    case Aggregated:
                        resp=AtpAg[cluster][col];
                    break;
                }
            break;
            case COX:
                switch (alg){
                    case FuzzyCMeans:
                        resp=CoxFCM[cluster][col];
                    break;
                    case KMeans3D:
                        resp=CoxK3[cluster][col];
                    break;
                    case KohonenMap:
                        resp=CoxKM[cluster][col];
                    break;
                    case Expert:
                        resp=CoxH[cluster][col];
                    break;
                    case Aggregated:
                        resp=CoxAg[cluster][col];
                    break;
                }
            break;
            
        }
        return resp;
    }
    
    static float getColorVar(Techniques tipo,Algorithms alg, int cluster,int col){
        float resp=255;
        switch (tipo){
            case NADH:
                switch (alg){
                    case FuzzyCMeans:
                        resp=NadhFCMVar[cluster][col];
                    break;
                    case  KMeans3D:
                        resp=NadhK3Var[cluster][col];
                    break;
                    case  KohonenMap:
                        resp=NadhKMVar[cluster][col];
                    break;
                    case  Expert:
                        resp=NadhHVar[cluster][col];
                    break;
                    case  Aggregated:
                        resp=NadhAgVar[cluster][col];
                    break;
                }
            break;
            case ATPASA:
                switch (alg){
                    case FuzzyCMeans:
                        resp=AtpFCMVar[cluster][col];
                    break;
                    case  KMeans3D:
                        resp=AtpK3Var[cluster][col];
                    break;
                    case  KohonenMap:
                        resp=AtpKMVar[cluster][col];
                    break;
                    case  Expert:
                        resp=AtpHVar[cluster][col];
                    break;
                    case  Aggregated:
                        resp=AtpAgVar[cluster][col];
                    break;
                }
            break;
            case COX:
                switch (alg){
                    case FuzzyCMeans:
                        resp=CoxFCMVar[cluster][col];
                    break;
                    case  KMeans3D:
                        resp=CoxK3Var[cluster][col];
                    break;
                    case  KohonenMap:
                        resp=CoxKMVar[cluster][col];
                    break;
                    case  Expert:
                        resp=CoxHVar[cluster][col];
                    break;
                    case  Aggregated:
                        resp=CoxAgVar[cluster][col];
                    break;
                }
            break;
            
        }
        return resp;
    }
}
