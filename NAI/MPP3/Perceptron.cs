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
            return sum > theta ? 1 : 0;
        }
        public Perceptron learn(List<LangSample> list)
        {
            for (int i = 0; i < list.Count; i++)
            {
                double sum = calculateSum(list[i]);
                bool equal = list[i].lang == lang;
                //Console.WriteLine(list[i].lang == lang);
                while (sum == (equal ? 0 : 1))
                //do
                {
                    for (int k = 0; k < input; k++) weight[k] = weight[k] + learningConst * list[i].letters[k] * (equal ? (1 - sum) : sum * -1);
                    theta += (1 - sum) * learningConst;
                    sum = calculateSum(list[i]);

                    //Console.WriteLine(string.Format("{0,10} eq: {1,5} sum: {2} sample: {3}", lang, equal, sum, i));
                    //foreach (var item in weight) Console.Write(string.Format("{0:N2}", item) + " ");
                    //Console.WriteLine();
                    //} while (sum == (equal ? 0 : 1));
                }
                int error = 0;
                for (int k = 0; k < i; k++) if ((list[i].lang == lang && calculateSum(list[i]) == 0) || (list[i].lang != lang && calculateSum(list[i]) == 1)) i = 0;
                //Console.WriteLine(error);
            }
            return this;
        }
        public bool testOne(string text)
        {
            return calculateSum(new LangSample(text, "test")) == 1;
        }
    }
}
