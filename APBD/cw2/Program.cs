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
            String urli = @"..\..\..\dane.csv";
            String urlo = @"wynik.xml";
            String format = "xml";
            IEnumerable<string> lines;
            try
            {
                lines = File.ReadLines(urli);
            }catch(FileNotFoundException e)
            {
                Console.Error.WriteLine(e.Message);
                return;
            }
            
            StreamWriter error = new StreamWriter(@"..\..\..\log.txt", false);
            HashSet<Student> hash = new HashSet<Student>(new OwnComparator());
            foreach (var line in lines)
            {
                string[] splits=line.Split(',');
                if (splits.Length != 9)
                {
                    error.WriteLine("Less or more than 9 columns:\t\t" + line);
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
                        error.WriteLine("One of column is blank:\t\t"+line);
                    }
                    else
                    {
                        if (!hash.Add(st))
                        {
                            error.WriteLine("Student already added:\t\t"+line);
                        }
                        else
                        {
                            //Console.WriteLine($"{st.GetHashCode()}{st}");
                        }
                        
                    }
                }
            }
            foreach (var stud in hash)
            {
                //Console.WriteLine(stud.GetHashCode());
            }
            Console.WriteLine(hash.Count);
            error.Dispose();
            error.Close();
        }
    }
}
