/*The goal of this class is to simulate a universe specified in one of the data files.
*/
public class NBody{
    public static double readRadius(String planetsTxtPath){
        In in = new In(planetsTxtPath);
        int nplanets = in.readInt();
        double radius = in.readDouble();
        return radius;

    }
    public static Planet[] readPlanets(String planetsTxtPath){
        In in = new In(planetsTxtPath);
        int nplanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[nplanets];
        for (int i=0; i<nplanets; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);



        }
        return planets;


    }
    public static void main(String [] args){
        double T = Double.parseDouble(args[0]);// convert strings to doubles
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int nplanets = planets.length;
        // drawing the background using StdDraw
        // set the scale so that it matches the radius of the universe
        StdDraw.setScale(-radius,radius);
        String background = "images/starfield.jpg";
        StdDraw.picture(0,0,background);
        // draw each one of the planets in the planets array you created
        for (Planet P : planets){
            P.draw();
        }
        // create an animation:
        //enable double buffering
        StdDraw.enableDoubleBuffering();
        double t = 0;

        //Calculate the net x and y forces for each planet
        while(t<T){
            double xForces [] = new double[nplanets];
            double yForces [] = new double[nplanets];
            //Calculate the net x and y forces for each planet
            for (int i = 0; i< nplanets; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);


            }
            //update on each of the planets.
            for (int i = 0; i<nplanets; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
                StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "images/" + planets[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;

        }
        //print out the final state of the universe in the same format as the input
        StdOut.print("%d\n", nplanets);
        StdOut.print("%.2e\n", radius);
        for(int i=0; i<nplanets; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);

        }









    }



}