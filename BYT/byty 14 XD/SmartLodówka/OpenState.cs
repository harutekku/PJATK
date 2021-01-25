using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace SmartLodówka
{
    class OpenState : State
    {
        Program Program;
        public OpenState(Program program)
        {
            Program = program;
            Console.WriteLine("Otwarto");
            program.Power = 0.1f;
            program.light = true;
            Program.PowerThread = new Thread(() =>
            {
                Program.ThreadBool = true;
                Program.Temperature = 15;
                while (Program.ThreadBool)
                {
                    if (new Random().Next(10) > 8) close(); //symuluję sprawdzanie czy użytkownik używa lodówki, tzn jeśli czujnik nie wykryje przez 5 sekund ruchu to automatycznie ją zamyka
                    Thread.Sleep(1000);
                }
            });
            Program.PowerThread.Start();
        }

        public void close()
        {
            Program.ThreadBool = false;
            Program.State = new ClosedState(Program);
        }

        public void defrost()
        {
            Program.ThreadBool = false;
            Program.State = new DefrostState(Program);
        }

        public Dish makeDish(Contents Contents, Packer Packer, string Name)
        {
            return Packer.createDish(Contents, Name);
        }

        public void open()
        {
            Console.Out.WriteLine("Już otwarta");
        }
    }
}
