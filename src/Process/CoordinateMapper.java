package Process;

/**
 * @author Omar López Ortega
 */
public class CoordinateMapper
{
    private double radio;
    private double phi;
    private double teta;    
    
    private double calcularRadio(int r, int g, int b)
    {
        radio = Math.sqrt(Math.pow(r, 2) + Math.pow(g, 2) + Math.pow(b, 2));
        //System.out.println("radio: " + radio);
        return radio;
    }
    private double calcularPhiGrados(int r, int g, int b)
    {
        phi  = this.calcularPhiRadianes(r, g, b);
        phi = Math.toDegrees(phi);
        //System.out.println("phiG: " + phi);
        return phi;        
    }
    private double calcularTetaGrados(int r, int g, int b)
    {
        teta = this.calcularTetaRadianes(r, g, b);
        teta = Math.toDegrees(teta);
        //System.out.println("tetaG: " + teta);
        return teta;        
    }
    
    private double calcularPhiRadianes(int r, int g, int b)
    {
        phi = (r/(radio*Math.sin(this.calcularTetaRadianes(r, g, b))));
        phi = Math.acos(phi);
        //System.out.println("phiR: " + phi);
        return phi;           
    }
    private double calcularTetaRadianes(int r, int g, int b)
    {
        teta = (b/radio);
        teta = Math.acos(teta);
        //System.out.println("tetaR: " + teta);
        return teta;        
    }
    private double calcularMetricaRadianes(int r, int g, int b)
    {
        radio = this.calcularRadio(r, g, b);
        teta = this.calcularTetaRadianes(r, g, b);
        phi = this.calcularPhiRadianes(r, g, b);
        //System.out.println("metricaR: " + radio*teta*phi);
        return radio*teta*phi;
    }
    private double calcularMetricaGrados(int r, int g, int b)
    {
        radio = this.calcularRadio(r, g, b);
        teta = this.calcularTetaGrados(r, g, b);
        phi = this.calcularPhiGrados(r, g, b);
        //System.out.println("metricaG: " + radio*teta*phi);
        return radio*teta*phi;
    }
    public double obtenerRadio(int r, int g, int b)
    {
        return this.calcularRadio(r, g, b);
    }
    public double obtenerTetaRadianes(int r, int g, int b)
    {
        return this.calcularTetaRadianes(r, g, b);
    }
    public double obtenerPhiRadianes(int r, int g, int b)
    {
        return this.calcularPhiRadianes(r, g, b);
    }
    public double obtenerPhiGrados(int r, int g, int b)
    {
        return this.calcularPhiGrados(r, g, b);
    }
    public double obtenerTetaGrados(int r, int g, int b)
    {
        return this.calcularTetaGrados(r, g, b);
    }
    public double obtenerMetricaGrados(int r, int g, int b)
    {
        return this.calcularMetricaGrados(r, g, b);
    }
    public double obtenerMetricaRadianes(int r, int g, int b)
    {
        return this.calcularMetricaRadianes(r, g, b);
    }
}
