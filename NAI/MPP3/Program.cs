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
            for (int i = 0; i < dirs.Length; i++) langs.Add(new Lang(dirs[i]));//zbieram jezyki

            List<LangSample> samples = new List<LangSample>();//zbieram probki
            for (int i = 0; i < langs[0].langSamples.Length; i++) for (int j = 0; j < langs.Count; j++) samples.Add(langs[j].langSamples[i]);

            Perceptron[] perceptrons = new Perceptron[langs.Count];
            for (int i = 0; i < perceptrons.Length; i++)
            {
                perceptrons[i] = new Perceptron(langs[i].lang, 0.001);
                perceptrons[i].learn(samples);
            }

            while (true)
            {
                Console.WriteLine("\nPodaj tekst (ctrl c dla wyjscia)");
                string test = Console.ReadLine();
                foreach (var perceptron in perceptrons) Console.WriteLine("To " + (perceptron.testOne(test) ? "" : "nie ") + perceptron.lang);
            }
        }
    }
}
