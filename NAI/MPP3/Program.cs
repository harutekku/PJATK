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
            string[] dirs = Directory.GetDirectories("..\\..\\..\\lang");
            List<Lang> langs = new List<Lang>();
            for (int i = 0; i < dirs.Length; i++) langs.Add(new Lang(dirs[i]));

            List<LangSample> samples = new List<LangSample>();
            for (int i = 0; i < langs[0].langSamples.Length; i++) for (int j = 0; j < langs.Count; j++) samples.Add(langs[j].langSamples[i]);

            Perceptron[] perceptrons = new Perceptron[langs.Count];
            for (int i = 0; i < perceptrons.Length; i++) perceptrons[i] = new Perceptron(langs[i].lang, 0.01).learn(samples);
            while (true)
            {
                Console.WriteLine("\nPodaj tekst (ctrl c dla wyjscia)");
                string test = Console.ReadLine();
                LangSample sample = new LangSample(test, "");
                Perceptron better = perceptrons[0];
                List<Perceptron> found = new List<Perceptron>();
                foreach (var perceptron in perceptrons)
                {
                    if (perceptron.testOne(sample)) found.Add(perceptron);
                    if (better.getSum(sample) < perceptron.getSum(sample)) better = perceptron;
                }
                if (found.Count > 1)
                {
                    Console.Write("\n\nRozpoznano: ");
                    foreach (var perc in found) Console.Write(perc.lang + ", ");
                    Console.WriteLine("\na najlepszy wynik mial " + better.lang + "\n\n");
                }
                else if (found.Count == 1) Console.Write("\n\nRozpoznano " + better.lang + "\n\n");
                else Console.Write("\n\nNie rozpoznano zadnego jezyka, ale najblizej byl " + better.lang + "\n\n");
            }
        }
    }
}
