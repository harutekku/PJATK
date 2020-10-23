using System;
using System.Diagnostics;
using System.IO;
using System.Text.RegularExpressions;

namespace mpp6
{
    class Program
    {
        static void Main(string[] args)
        {
            int setNum = new Random().Next(1, 15);
            var dataLines = File.ReadAllLines("..\\..\\..\\plecak.txt");
            var length = int.Parse(dataLines[0].Split(" ")[2].Split(",")[0]);
            length--;
            length--;
            length--;
            var capacity = int.Parse(dataLines[0].Split(" ")[4]);
            var dataName = dataLines[setNum * 4 - 3];
            var sizeLine = dataLines[setNum * 4 - 2].Split("{")[1].Split("}")[0].Replace(',', ' ');
            var valLine = dataLines[setNum * 4 - 1].Split("{")[1].Split("}")[0].Replace(',', ' ');
            int[] sizes = Array.ConvertAll(Regex.Replace(sizeLine, " {2,}", " ").Split(" "), s => int.Parse(s));
            int[] values = Array.ConvertAll(Regex.Replace(valLine, " {2,}", " ").Split(" "), s => int.Parse(s));
            BackPack bestBackPack = new BackPack(0);
            var stopwatch = new Stopwatch();
            stopwatch.Start();
            for (long i = 1; i < Math.Pow(2, length); i++)
            {
                var backPack = new BackPack(i);
                for (int j = 0; j < length; j++)
                {
                    if (((i >> j) & 1) == 1)
                    {
                        if (backPack.size + sizes[j] > capacity) continue;
                        backPack.size += sizes[j];
                        backPack.value += values[j];
                    }
                }
                if (backPack.size <= capacity && bestBackPack.value < backPack.value) bestBackPack = backPack;
            }
            stopwatch.Stop();
            var elapsed_time = stopwatch.ElapsedMilliseconds;
            Console.WriteLine("Time: " + elapsed_time + " ms\n" + dataName + "\nBackpach weight: " + bestBackPack.size + "\nBackpack value: " + bestBackPack.value + "\n");
            for (int j = 0; j < length; j++) if (((bestBackPack.combination >> j) & 1) == 1) Console.WriteLine("Id: " + (j + 1) + " Size: " + sizes[j] + " Value: " + values[j]);
        }
    }
    class BackPack
    {
        public int size, value;
        public long combination;
        public BackPack(long combination)
        {
            size = value = 0;
            this.combination = combination;
        }
    }
}
