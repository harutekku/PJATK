using System;
using System.Collections.Generic;
using System.IO;

namespace cw2
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            String urli = "z:\\PJATK\\apbd\\cw2\\dane.csv";
            String urlo = "C:\\Users\\Jan\\Desktop\\wynik.xml";
            String format = "xml";
            var lines = File.ReadLines(urli);
            System.IO.StreamWriter error = new System.IO.StreamWriter(@"z:\PJATK\APBD\cw2\log.txt", false);
            var hash = new HashSet<Student>();
            foreach (var line in lines)
            {
                string[] splits=line.Split(',');
                if (splits.Length != 9)
                {
                    error.WriteLine("Error in line:\t\t" + line);
                }
                else
                {
                    var st = new Student
                    {
                        Imie = splits[0],
                        Nazwisko = splits[1],
                        Kierunek = splits[2],
                        Tryb = splits[3],
                        Eska = splits[4],
                        DataUr = DateTime.Parse(splits[5]),
                        Mail = splits[6],
                        ImieMatki = splits[7],
                        ImieOjca = splits[8]
                    };
                    if (!st.poprawnosc())
                    {
                        error.WriteLine("maslo");//todo
                    }
                    else
                    {
                        if (!hash.Add(st))
                        {
                            error.WriteLine(st.get());
                        }
                    }
                }
            }
        }
    }
}
