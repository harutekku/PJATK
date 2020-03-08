using System;

namespace zad1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 20;//dlugosc rozwiniecia
            sin(0, n);
            sin(90, n);
            sin(180, n);
            sin(270, n);
            sin(360, n);
            sin(360+90, n);
            sin(15, n);
            sin(-15, n);

            System.Console.WriteLine();
            System.Console.WriteLine();
            System.Console.WriteLine();
            Random rd = new Random();
            for(int i = 0; i < 50; i++)
            {
                sin(rd.Next(-720, 720), rd.Next(0,20));
            }
        }
        static double sin(double deg, int n)
        {
            deg = deg % 360;
            double x;
            bool positive = true;
            if (deg <= 90)
            {
                x = deg;
            }else if (deg <= 180)
            {
                x = 180 - deg;
            }else if (deg <= 270)
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
            double rad = ((x * Math.PI) / 180);
            
            double result = 0;
            int hop = 1;
            while (hop<=n)
            {
                if (positive) result += Math.Pow(rad, hop) / factorial(hop);
                else result -= Math.Pow(rad, hop) / factorial(hop);
                hop += 2;
                positive = !positive;
            }
            Console.WriteLine("Sinus z " + deg + " o rozwinięciu " + n + " wynosi " + result);
            return result;
        }
        static double factorial(double fac)
        {
            double result = 1;
            while (fac > 1) result *= fac--;
            return result;
        }
    }
}
