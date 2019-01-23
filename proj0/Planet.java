/**Project0: NBdody simulation 
 */
/**Building the planet class and its constructor */
public class Planet{
    public double xxPos; // its current x position
    public double yyPos; // its current y position
    public double xxVel; //its current velocity in x direction
    public double yyVel; //its current velocity in y direction
    public double mass;
    public String imgFileName; //the name of the file that corresponds to the image that depicts the planet.
    static final double G = 6.67e-11;//It is good practice to declare any constants as a ‘static final’ variable in your class, and to use that variable anytime you wish to use the constant.
    // signature of the first constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    // signature of the second constructor
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    // calculates the distance between two Planets
    public double calcDistance(Planet p){
        double dis = (p.xxPos-this.xxPos)*(p.xxPos-this.xxPos)+(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos);
        dis = Math.sqrt(dis);
        return dis;
    }
    //takes in a planet, 
    //and returns a double describing the force exerted on this planet by the given planet.
    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        double f = G * this.mass * p.mass/(r * r);
        return f;

    }
    //describe the force exerted in the X direction
    public double calcForceExertedByX(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double fx = f * dx /r ;
        return fx;

    }
     //describe the force exerted in the Y direction
     public double calcForceExertedByY(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double fy = f * dy /r ;
        return fy;
    }
    // take in an array of Planets and calculate the net X and net Y force 
    //exerted by all planets in that array upon the current Planet
    public double calcNetForceExertedByX(Planet[] planets){
        double netfx = 0;
        for(Planet p: planets){
            
            if(this.xxPos == p.xxPos && this.yyPos == p.yyPos){
                continue;
            }else{
                netfx += calcForceExertedByX(p);

            }
        }
        return netfx;


    }

    public double calcNetForceExertedByY(Planet[] planets){
        double netfy= 0;
        for(Planet p: planets){
            
            if(this.xxPos == p.xxPos && this.yyPos == p.yyPos){
                continue;
            }else{
                netfy += calcForceExertedByY(p);

            }
        }
        return netfy;
    }
    //determines how much the forces exerted on the planet will cause that planet to accelerate, 
    //and the resulting change in the planet’s velocity and position in a small period of time dt
    public Planet update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
        return this;

    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos, "images/"+ imgFileName);
        StdDraw.show();
    }



}