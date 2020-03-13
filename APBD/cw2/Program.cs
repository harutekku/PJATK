using System;
using System.Collections.Generic;
using System.IO;
using System.Xml.Serialization;

namespace cw2
{
    class Program
    {
        static void Main(string[] args)
        {
            String urli = @"..\..\..\dane.csv";
            String urlo = @"..\..\..\result.xml";
            String format = @"xml";
            IEnumerable<string> lines;
            if (args.Length == 3)
            {
                urli = args[0];
                urlo = args[1];
                format = args[2];
            }
            else
            {
                Console.WriteLine("Too few arguments, using default settings");
            }
            try
            {
                if (!(format == "xml" || format == "json")) throw new ArgumentException("Bad format");
                if (!Path.HasExtension(urli)) throw new ArgumentException("Bad path");
                lines = File.ReadLines(urli);
            }
            catch (ArgumentException e)
            {
                Console.Error.WriteLine(e.Message);
                return;
            }
            catch (FileNotFoundException e)
            {
                Console.Error.WriteLine(e.Message);
                return;
            }

            XMLBox box = new XMLBox
            {
                createdAt = DateTime.Today.ToString("dd.MM.yyyy"),
                author = "Jakub Pawłowicz"
            };
            StreamWriter error = new StreamWriter(@"..\..\..\log.txt", false);
            box.studenci = new HashSet<Student>(new OwnComparator());
            foreach (var line in lines)
            {
                string[] splits = line.Split(',');
                if (splits.Length != 9)
                {
                    error.WriteLine("Less or more than 9 columns:\t\t" + line);
                    continue;
                }
                var st = new Student
                {
                    Imie = splits[0],
                    Nazwisko = splits[1],
                    Studies = new Studies
                    {
                        faculty = splits[2],
                        mode = splits[3]
                    },
                    Eska = splits[4],
                    DataUr = DateTime.Parse(splits[5]).ToString("dd.MM.yyyy"),
                    Mail = splits[6],
                    ImieMatki = splits[7],
                    ImieOjca = splits[8]
                };
                if (!st.poprawnosc())
                {
                    error.WriteLine("One of column is blank:\t\t" + line);
                    continue;
                }
                if (!box.studenci.Add(st))
                {
                    error.WriteLine("Student already added:\t\t" + line);
                }
            }
            error.Dispose();
            Console.WriteLine(box.studenci.Count);
            var studiesMap = new Dictionary<string, int>();
            foreach (var st in box.studenci)
            {
                if (!studiesMap.ContainsKey(st.Studies.faculty)) studiesMap[st.Studies.faculty] = 1;
                else studiesMap[st.Studies.faculty] += 1;
            }
            var studiesList = new List<NumberOfStudents>();
            foreach (var item in studiesMap)
            {
                Console.WriteLine(item.Key + " " + item.Value);
                studiesList.Add(new NumberOfStudents { name = item.Key, numberOfStudents = item.Value });
            }
            box.activeStudents = studiesList;
            FileStream writer = new FileStream(urlo, FileMode.Create);
            XmlSerializer serializer = new XmlSerializer(typeof(XMLBox), new XmlRootAttribute("uczelnia"));
            var XmlSerializerNamespaces = new XmlSerializerNamespaces();
            XmlSerializerNamespaces.Add("", "");
            serializer.Serialize(writer, box, XmlSerializerNamespaces);

        }
    }
}
