using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace MPP3
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = Encoding.ASCII;
            //string[] allfiles = Directory.GetFiles("..\\..\\..\\lang", "*.txt*", SearchOption.AllDirectories);
            string[] dirs = Directory.GetDirectories("..\\..\\..\\lang");
            List<Lang> langs = new List<Lang>();
            //Lang[] langs = new Lang[dirs.Length];
            for (int i = 0; i < dirs.Length; i++)
            {
                langs.Add(new Lang(dirs[i]));
            }
            /*foreach (var lang in langs)
            {
                foreach (var sample in lang.samples)
                {
                    Console.WriteLine(sample);
                }
            }*/
            Perceptron[] perceptrons = new Perceptron[dirs.Length];
            for (int i = 0; i < perceptrons.Length; i++)
            {
                perceptrons[i] = new Perceptron(dirs[i], 0.001);
                perceptrons[i].learn(langs);
            }
            while (true)
            {
                Console.WriteLine("\nPodaj tekst (ctrl c dla wyjscia)");
                try
                {
                    string test = Console.ReadLine();
                    foreach (var perceptron in perceptrons)
                    {
                        if (perceptron.testOne(test)) Console.WriteLine("To " + perceptron.lang);
                        else Console.WriteLine("To nie " + perceptron.lang);
                    }
                }
                catch (Exception e) { Console.WriteLine("Zakończono program"); }
            }


        }
    }
    public class Lang
    {
        public string lang { get; set; }
        public string[] samples { get; set; }
        public LangSample[] langSamples { get; set; }
        public Lang(string lang)
        {
            string[] l = lang.Split('\\');
            this.lang = l[l.Length-1];
            this.samples = Directory.GetFiles(lang, "*.txt");
            langSamples = new LangSample[this.samples.Length];
            for (int i = 0; i < samples.Length; i++)
            {
                langSamples[i] = new LangSample(File.ReadAllText(samples[i], Encoding.ASCII));
            }
        }
        public string getText(int number_of_sample)
        {
            string all = File.ReadAllText(samples[number_of_sample], Encoding.ASCII);
            StringBuilder stringBuilder = new StringBuilder();
            foreach (char charek in all.ToLower())
            {
                if (charek >= 'a' && charek <= 'z')
                {
                    stringBuilder.Append(charek);
                }
            }
            return stringBuilder.ToString();
        }
    }
    public class LangSample
    {
        public double[] letters { get; set; }
        public LangSample(string text)
        {
            text = text.ToLower();
            letters = new double['z' - 'a' + 1];
            int counter = 0;
            for (int i = 0; i < letters.Length; i++)
            {
                letters[i] = 0;
            }
            foreach (char charek in text)
            {
                if (charek >= 'a' && charek <= 'z')
                {
                    letters[charek - 'a']++;
                    counter++;
                }
            }
            for (int i = 0; i < letters.Length; i++)
            {
                letters[i] /= counter;
                letters[i] *= 100;
                //Console.Write(string.Format("{0:N2}", letters[i]) + " ");
            }
            //Console.WriteLine();
        }
    }
    public class Perceptron
    {
        int input;
        double[] weight;
        double theta, learningConst;
        public string lang;
        public Perceptron(string lang, double learningConst)
        {
            this.input = 'Z' - 'A';
            string[] l = lang.Split('\\');
            this.lang = l[l.Length - 1];
            this.weight = new double[input];
            this.theta = 1;
            this.learningConst = learningConst <= 0 ? 0.5 : learningConst > 1 ? 0.5 : learningConst;
            Random random = new Random(DateTime.Now.Millisecond);
            for (int i = 0; i < this.input; i++) weight[i] = random.Next(1);
        }
        public double calculateSum(LangSample lang)
        {
            double sum = 0;
            for (int i = 0; i < input; i++) sum += lang.letters[i] * weight[i];
            return sum > theta ? 1 : 0;
        }
        public Perceptron learn(List<Lang> list)
        {
            for (int i = 0; i < list.Count; i++)
            {
                for (int j = 0; j < list[i].langSamples.Length; j++)
                {
                    double sum = calculateSum(list[i].langSamples[j]);
                    bool equal = list[i].lang == lang;
                    while (sum == (equal ? 0 : 1))
                    {
                        for (int k = 0; k < input; k++) weight[k] = weight[k] + learningConst * list[i].langSamples[j].letters[k] * (equal ? (1 - sum) : sum * -1);
                        theta += (1 - sum) * learningConst;
                        sum = calculateSum(list[i].langSamples[j]);

                        /*Console.Write(lang + " " + j + " " + theta + " ");
                        foreach (var item in weight)
                        {
                            Console.Write(string.Format("{0:N1}", item) + " ");
                        }
                        Console.WriteLine();*/

                    }
                    //for (int k = 0; k < j; k++) if ((list[j].lang == lang && calculateSum(list[j].langSamples[k]) == 0) || (list[j].lang != lang && calculateSum(list[j].langSamples[k]) == 1)) j = 0;
                }
            }
            return this;
        }
        public bool testOne(string text)
        {
            return calculateSum(new LangSample(text)) == 1;
        }
    }
}
