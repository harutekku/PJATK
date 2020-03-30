using System;
using System.Collections.Generic;
using System.IO;

namespace MPP2
{
    class Program
    {
        static void Main(string[] args)
        {
            List<SCP> trainingList = new List<SCP>(), testList = new List<SCP>(); ;
            string[] trainingLines = File.ReadAllLines("..\\..\\..\\iris_training.txt"), testLines = File.ReadAllLines("..\\..\\..\\iris_test.txt");
            //string[] testLines = File.ReadAllLines("..\\..\\..\\iris_training.txt"), trainingLines = File.ReadAllLines("..\\..\\..\\iris_test.txt");
            foreach (string line in trainingLines) { SCP p = new SCP(line, true); trainingList.Add(p); }
            foreach (string line in testLines) { SCP p = new SCP(line, true); testList.Add(p); }
            Perceptron magic = new Perceptron(trainingList[0].points.Length, "Iris-setosa", 0.5);
            magic.learn(trainingList).test(testList).testOne();
        }
    }
    public class Perceptron
    {
        int input;
        double[] weight;
        double theta, learningConst;
        string type;
        public Perceptron(int input, string type, double learningConst)
        {
            this.input = input;
            this.type = type;
            this.weight = new double[input];
            this.theta = 1;
            this.learningConst = learningConst <= 0 ? 0.5 : learningConst > 10 ? 0.5 : learningConst;
            Random random = new Random(DateTime.Now.Millisecond);
            for (int i = 0; i < this.input; i++) weight[i] = random.Next(1);
        }
        public double calculateSum(SCP scp)
        {
            double sum = 0;
            for (int i = 0; i < input; i++) sum += scp.points[i] * weight[i];
            return sum > theta ? 1 : 0;
        }
        public Perceptron learn(List<SCP> list)
        {
            for (int i = 0; i < list.Count; i++)
            {
                double sum = calculateSum(list[i]);
                bool equal = list[i].type == type;
                while (sum == (equal ? 0 : 1))
                {
                    for (int j = 0; j < input; j++) weight[j] = weight[j] + learningConst * list[i].points[j] * (equal ? (1 - sum) : sum * -1);
                    theta += (1 - sum) * learningConst;
                    sum = calculateSum(list[i]);
                }
                for (int k = 0; k < i; k++) if ((list[k].type == type && calculateSum(list[k]) == 0) || (list[k].type != type && calculateSum(list[k]) == 1)) i = 0;
            }
            return this;
        }
        public Perceptron test(List<SCP> list)
        {
            //foreach(var waga in weight)Console.WriteLine(waga);
            
            int correct = 0, all = 0, other = 0, allother = 0;
            foreach (SCP scp in list)
            {
                if (scp.type == type) all++; else allother++;
                if (calculateSum(scp) == 1) correct++; else other++;
            }
            Console.WriteLine(string.Format("Prawidłowo znaleziono {0,2} Iris-setosa czyli {1:N2}% wszystkich\nPrawidłowo odrzucono {2,3} innych czyli {3,11:N2}% wszystkich", correct, (double)correct / all * 100, other, (double)other / allother * 100));
            return this;
        }
        public Perceptron testOne()
        {
            while (true)
            {
                Console.WriteLine("\nPodaj punkty (Cokolwiek innego dla wyjscia)");
                try
                {
                    SCP scp = new SCP(Console.ReadLine(), false);
                    Console.WriteLine("To" + (calculateSum(scp) == 1 ? " " : " nie ") + "Iris-setosa\n");
                }
                catch (Exception e) { Console.WriteLine("Zakończono program"); return this; }
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
            points = new double[ifTest ? element.Length - 1 : element.Length];
            for (int i = 0; i < points.Length; i++) points[i] = double.Parse(element[i]);
            if (ifTest) type = element[element.Length - 1];
        }
    }
}