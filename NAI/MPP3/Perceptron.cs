using Microsoft.VisualBasic.CompilerServices;
using System;
using System.Collections.Generic;

namespace MPP3
{
    public class Perceptron
    {
        public int input;
        public double[] weight;
        public double theta, learningConst;
        public string lang;
        public Perceptron(string lang, double learningConst)
        {
            this.lang = lang;
            this.learningConst = learningConst <= 0 ? 0.5 : learningConst > 1 ? 0.5 : learningConst;

            input = 'Z' - 'A';
            weight = new double[input];
            theta = 1;

            Random random = new Random(DateTime.Now.Millisecond);
            for (int i = 0; i < input; i++) weight[i] = random.Next(0, 1);
        }
        public double calculateSum(LangSample lang)
        {
            double sum = 0;
            for (int i = 0; i < input; i++) sum += lang.letters[i] * weight[i];
            return sum;
        }
        public Perceptron learn(List<LangSample> list)
        {
            for (int i = 0; i < list.Count; i++)
            {
                bool equal = list[i].lang == lang;
                double sum = calculateSum(list[i]);
                bool pctResponse = sum >= theta;
                while (pctResponse != equal)
                {
                    for (int k = 0; k < input; k++) weight[k] += learningConst * list[i].letters[k] * ((equal ? 1 : 0) - (pctResponse ? 1 : 0));
                    theta += learningConst * ((equal ? 1 : 0) - (pctResponse ? 1 : 0));
                    pctResponse = sum >= theta;
                    sum = calculateSum(list[i]);
                    //Console.WriteLine(string.Format("{0,10}\tsample: {3,2},\tprobka: {1,10}\twynik z probki: {2,5}\ttheta: {4,5:N3}", lang, list[i].lang, pctResponse, i, theta));
                    //foreach (var item in weight) Console.Write(string.Format("{0,5:N2}", item) + " ");
                    //Console.WriteLine("\n");    
                }

                for (int k = 0; k < i; k++) if ((list[i].lang == lang && calculateSum(list[i]) == 0) || (list[i].lang != lang && calculateSum(list[i]) == 1))
                    {
                        Console.WriteLine("Back");
                        i = 0;
                    }
            }
            return this;
        }
        public bool testOne(LangSample test)
        {
            return calculateSum(test) >= theta;
        }
        public double getSum(LangSample test)
        {
            return calculateSum(test);
        }
    }
}
