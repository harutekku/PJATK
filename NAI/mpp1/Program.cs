using System;
using System.Collections.Generic;
using System.IO;

namespace mpp1
{
    class Program
    {
        static void Main(string[] args)
        {
            List<SCP> list = new List<SCP>();
            HashSet<string> keys = new HashSet<string>();
            string[] lines = File.ReadAllLines("..\\..\\..\\iris_training.txt");
            foreach (string line in lines)
            {
                SCP p = new SCP(line);
                list.Add(p);
                keys.Add(p.type);
            }
            //Console.WriteLine("\nPodaj k");
            //int k = int.Parse(Console.ReadLine());
            void licz(int k)
            {
                int n = keys.Count;
                int pointCounter = 0;
                int correctAnswers = 0;

                string[] newPoints = File.ReadAllLines("..\\..\\..\\iris_test.txt");
                foreach (string line in newPoints)
                {
                    SCP point = new SCP(line);
                    double[] nearestDistance = new double[k];
                    for (int i = 0; i < k; i++) nearestDistance[i] = double.MaxValue;
                    SCP[] nearestPoints = new SCP[k];
                    double distance;
                    foreach (var point2 in list)
                    {
                        distance = SCP.distance(point, point2);
                        //Console.WriteLine("Dystans " + distance);
                        /*for (int i = 0; i < k; i++)
                        {
                            if (distance < nearestDistance[i])
                            {
                                for (int j = k - 1; j > i; j--)
                                {
                                    nearestDistance[j] = nearestDistance[j - 1];
                                    nearestPoints[j] = nearestPoints[j - i];
                                }
                                nearestDistance[i] = distance;
                                nearestPoints[i] = point2;
                            }
                        }*/
                        for (int i = k - 1; i >= 0; i--)
                        {
                            if (distance < nearestDistance[i])
                            {
                                if (i != k - 1)
                                {
                                    nearestDistance[i + 1] = nearestDistance[i];
                                    nearestPoints[i + 1] = nearestPoints[i];
                                }
                                nearestDistance[i] = distance;
                                nearestPoints[i] = point2;
                            }
                        }
                    }
                    //foreach (double item in nearestDistance) Console.Write(string.Format("{0:N2}", item) + " ");Console.WriteLine();
                    Dictionary<string, int> counter = new Dictionary<string, int>();
                    foreach (var item in nearestPoints)
                    {
                        if (counter.ContainsKey(item.type))
                            counter[item.type]++;
                        else
                            counter.Add(item.type, 1);
                    }
                    string mostPopularType = "";
                    int mostPopularNumber = 0;
                    foreach (var item in counter)
                    {
                        if (mostPopularNumber < item.Value)
                        {
                            mostPopularNumber = item.Value;
                            mostPopularType = item.Key;
                        }
                    }
                    //Console.WriteLine("Wybrano typ " + mostPopularType + " a prawidłowym jest " + point.type);
                    pointCounter++;
                    if (mostPopularType == point.type) correctAnswers++;
                }
                Console.WriteLine("k= "+k+" " + correctAnswers);
            }
            for(int i = 0; i < 120; i++)
            {
                licz(i);
            }

        }
    }
    public class SCP
    {
        public double[] points;
        public string type;
        public SCP(string line)
        {
            char[] splits = new char[] { ' ', '\t' };
            string[] element = line.Split(splits, StringSplitOptions.RemoveEmptyEntries);
            points = new double[element.Length - 1];
            for (int i = 0; i < element.Length - 1; i++)
            {
                points[i] = double.Parse(element[i]);
            }
            type = element[element.Length - 1];
        }
        public static double distance(SCP s1, SCP s2)
        {
            double power = 0;
            for (int i = 0; i < s1.points.Length; i++)
            {
                power += Math.Pow(s1.points[i] - s2.points[i], 2);
            }
            return power;
        }
    }
}
