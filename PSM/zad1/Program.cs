using System;

namespace zad1
{
    class Program
    {
        static void Main(string[] args)
        {
            double pi = Math.PI;
            sin(0,true);
            sin(90, true);
            sin(180, true);
            sin(270, true);
            sin(360, true);
            sin(360+90, true);
            sin(45, true);
            sin(-45, true);
            System.Console.WriteLine();
            sin(0, false);
            sin(pi/2, false);
            sin(pi, false);
            sin(pi*3/2, false);
            sin(pi*2, false);
            sin(pi/4, false);
            sin(pi/-4, false);
            System.Console.WriteLine();
            System.Console.WriteLine();
            Random rd = new Random();
            for(int i = 0; i < 10; i++)
            {
                sin(rd.Next(-720, 720), rd.Next(0,10)>5);
            }
        }
        static double sin(double deg, bool czyStopnie)
        {
            if (!czyStopnie)
            {
                deg = deg * 180 / Math.PI;
            }
            deg = deg % 360;
            double x;
            bool positive = true;
            if (deg <= 90)
            {
                x = deg;
            }
            else if (deg <= 180)
            {
                x = 180 - deg;
            }
            else if (deg <= 270)
            {
                x = deg - 180;
                positive = !positive;
            }
            else if (deg <= 360)
            {
                x = 360 - deg;
                positive = !positive;
            }
            else
            {
                Console.Error.WriteLine("Error");
                return -1;
            }
            bool positiveForMath = positive;
            double rad = ((x * Math.PI) / 180);
            double result = 0;
            int hop = 1;
            double factorial = 1;
            Console.WriteLine("Sinus z " + (czyStopnie?deg+" stopni":rad+" radianów"));
            for(int i=1;i<=10;i++)
            {
                if (positive) result += Math.Pow(rad, hop) / factorial;
                else result -= Math.Pow(rad, hop) / factorial;
                factorial *= ++hop;
                factorial *= ++hop;
                positive = !positive;
                Console.WriteLine("o kroku " + i + "\twynosi " + result + "\tz biblioteki " + Math.Sin(positiveForMath ? rad : rad * -1) + 
                    "\t roznica " + Math.Abs(result - Math.Sin(positiveForMath ? rad : rad * -1)));
            }
            Console.WriteLine();
            return result;
        }
    }
}
