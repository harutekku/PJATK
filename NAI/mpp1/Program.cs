using System;
using System.Collections.Generic;
using System.IO;

namespace mpp1
{
    class Program
    {
        public static Random rd = new Random(DateTime.Now.Millisecond);
        static void Main(string[] args)
        {
            List<SCP> list = new List<SCP>();
            string[] lines = File.ReadAllLines("..\\..\\..\\iris_training.txt");
            foreach (string line in lines)
            {
                SCP p = new SCP(line, true);
                list.Add(p);
            }
            Console.WriteLine("\nPodaj k");
            int k = int.Parse(Console.ReadLine());
            string[] newPoints = File.ReadAllLines("..\\..\\..\\iris_test.txt");
            void przeliczDlaK(int k)
            {
                int pointCounter = 0;
                int correctAnswers = 0;
                foreach (string line in newPoints)
                {
                    SCP point = new SCP(line, true);
                    if (point.Nearest(list, k) == point.type) correctAnswers++;
                    pointCounter++;
                }
                Console.WriteLine(string.Format("dla k: {0,3}, prawidłowo zakwalifikowano {1,2} czyli {2:N2}%", k, correctAnswers, correctAnswers / (double)pointCounter * 100));
            }
            //for(int i = 1; i <= list.Count; i++)przeliczDlaK(i);
            przeliczDlaK(k);
            while (true)
            {
                Console.WriteLine("\nPodaj punkty ('z' dla wyjscia)");
                try
                {
                    SCP myPoint = new SCP(Console.ReadLine(), false);
                    Console.WriteLine("Wybrano typ: " + myPoint.Nearest(list, k));
                }
                catch (FormatException e) { return; }
            }
        }
    }
    public class SCP
    {
        public double[] points { get; set; }
        public string type;
        public SCP(string line, bool ifTest)
        {
            string[] element = line.Split(new char[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries);
            int len;
            if (ifTest) len = element.Length - 1;
            else len = element.Length;
            points = new double[len];
            for (int i = 0; i < len; i++)points[i] = double.Parse(element[i]);
            if (ifTest) type = element[element.Length - 1];
        }
        public double Distance(SCP s2)
        {
            double power = 0;
            for (int i = 0; i < points.Length; i++) power += Math.Pow(points[i] - s2.points[i], 2);
            return power;
        }
        public string Nearest(List<SCP> list, int k)
        {
            List<Tuple<SCP, double>> distance = new List<Tuple<SCP, double>>();
            foreach (SCP Scp in list)
            {
                double dst = Distance(Scp);
                distance.Add(new Tuple<SCP, double>(Scp, Distance(Scp)));
            }
            distance.Sort(new OwnComparator());
            //foreach (Tuple<SCP,double> tuple in distance)Console.WriteLine(tuple.Item1.type + " " + tuple.Item2);
            Dictionary<string, int> counter = new Dictionary<string, int>();
            foreach (Tuple<SCP, double> tuple in distance.GetRange(0, k))
            {
                if (counter.ContainsKey(tuple.Item1.type)) counter[tuple.Item1.type]++;
                else counter.Add(tuple.Item1.type, 1);
            }
            //foreach (var item in counter) Console.WriteLine(item.Key + " " + item.Value);Console.WriteLine();
            int mostPopularNumber = 0;
            foreach (var item in counter) if (mostPopularNumber < item.Value) mostPopularNumber = item.Value;
            List<String> result = new List<string>();
            foreach (var item in counter) if (mostPopularNumber == item.Value) result.Add(item.Key);
            //foreach (var item in result)Console.WriteLine(item);
            {
                return result[Program.rd.Next(0, result.Count)];
            }
        }
    }
    public class OwnComparator : IComparer<Tuple<SCP, double>>
    {int IComparer<Tuple<SCP, double>>.Compare(Tuple<SCP, double> x, Tuple<SCP, double> y) { return (int)Math.Ceiling(x.Item2 - y.Item2); }}
}