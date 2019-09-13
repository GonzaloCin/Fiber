package Process;

/**
 * @author Omar López Ortega
 */
public class DistanceCalculator
{
    CoordinateMapper t = new CoordinateMapper();
    private double calculaDiferenciaLineal(double m1, double m2)
    {
        return Math.abs(m1- m2);        
    }
    private double calculaDiferenciaCuadratica(double m1, double m2)
    {
        return Math.sqrt(Math.abs(Math.pow(m1, 2) - Math.pow(m2, 2)));
    }
    private double calculaDiferenciaMediaC(double m1, double m2)
    {
        return (0.5)*Math.pow((m1-m2),2);
    }
    private double calculaDiferenciaDivergencia(double m1, double m2)
    {
        return Math.sqrt(0.5*Math.pow((m1-m2)/(m1+m2),2));
    }
    private double calculaDistanciaDeltaC(int r, int g, int b, int indice)
    {
        double radio = t.obtenerRadio(r, g, b);
        double teta = t.obtenerTetaRadianes(r, g, b);
        double phi = t.obtenerPhiRadianes(r, g, b);
        double delta1 = this.calculaDiferenciaCuadratica(radio, t.obtenerRadio(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta2 = this.calculaDiferenciaCuadratica(teta, t.obtenerTetaRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta3 = this.calculaDiferenciaCuadratica(phi, t.obtenerPhiRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));        
        return (delta1 + delta2 + delta3);
    }
    private double calculaDistanciaDeltaL(int r, int g, int b, int indice)
    {
        double radio = t.obtenerRadio(r, g, b);
        double teta = t.obtenerTetaRadianes(r, g, b);
        double phi = t.obtenerPhiRadianes(r, g, b);
        double delta1 = this.calculaDiferenciaLineal(radio, t.obtenerRadio(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta2 = this.calculaDiferenciaLineal(teta, t.obtenerTetaRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta3 = this.calculaDiferenciaLineal(phi, t.obtenerPhiRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));        
        return (delta1 + delta2 + delta3);   
    }
    private double calculaDistanciaDeltaMC(int r, int g, int b, int indice)
    {
        double radio = t.obtenerRadio(r, g, b);
        double teta = t.obtenerTetaRadianes(r, g, b);
        double phi = t.obtenerPhiRadianes(r, g, b);
        double delta1 = this.calculaDiferenciaMediaC(radio, t.obtenerRadio(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta2 = this.calculaDiferenciaMediaC(teta, t.obtenerTetaRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta3 = this.calculaDiferenciaMediaC(phi, t.obtenerPhiRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));        
        return (delta1 + delta2 + delta3);
    }
    private double calculaDistancaDeltaDiver(int r, int g, int b, int indice)
    {
        double radio = t.obtenerRadio(r, g, b);
        double teta = t.obtenerTetaRadianes(r, g, b);
        double phi = t.obtenerPhiRadianes(r, g, b);
        double delta1 = this.calculaDiferenciaDivergencia(radio, t.obtenerRadio(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta2 = this.calculaDiferenciaDivergencia(teta, t.obtenerTetaRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));
        double delta3 = this.calculaDiferenciaDivergencia(phi, t.obtenerPhiRadianes(ReferenceSetter.obtenR(indice), ReferenceSetter.obtenG(indice),ReferenceSetter.obtenB(indice)));        
        return (delta1 + delta2 + delta3);
    }
    public double obtenerDistanciaLineal(double m1, double m2)
    {
        return this.calculaDiferenciaLineal(m1, m2);
    }
    public double obtenerDistanciaCuadratica(double m1, double m2)
    {
        return this.calculaDiferenciaCuadratica(m1, m2);
    }
    public double obtenerDistanciaDeltaC(int r, int g, int b, int indice)
    {
        return this.calculaDistanciaDeltaC(r, g, b, indice);
    }
    public double obtenerDistanciaDeltaL(int r, int g, int b, int indice)
    {
        return this.calculaDistanciaDeltaL(r, g, b, indice);
    }
    public double obtenerDistanciaDeltaMC(int r, int g, int b, int indice)
    {
        return this.calculaDistanciaDeltaMC(r, g, b, indice);
    }
    public double obtenerDistanciaDeltaDiverg(int r, int g, int b, int indice)
    {
        return this.calculaDistancaDeltaDiver(r, g, b, indice);
    }
}
