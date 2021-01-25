using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace SmartLodówka
{
    class ClosedState : State
    {
        private Program Program;

        public ClosedState(Program program)
        {
            Program = program;
            Console.WriteLine("Zamknięto");
            Program.light = false;
            Program.PowerThread = new Thread(() =>
              {
                  Program.ThreadBool = true;
                  while (Program.ThreadBool)
                  {
                      Console.Out.WriteLine("Temperatura: " + Program.Temperature);
                      if (Program.Temperature > 10)
                      {
                          Program.Power = 1;
                          Program.Temperature -= 2;
                      }
                      else if (Program.Temperature > 6)
                      {
                          Program.Power = 0.6f;
                          Program.Temperature -= 1.3f;
                      }
                      else if (Program.Temperature > 4)
                      {
                          Program.Power = 0.4f;
                          Program.Temperature -= 1f;
                      }
                      else if (Program.Temperature > 2)
                      {
                          Program.Power = 0.2f;
                          Program.Temperature -= 0.4f;
                      }
                      else
                      {
                          Program.Power = 0.1f;
                      }
                      Thread.Sleep(500);
                      
                  }
              });
            Program.PowerThread.Start();
        }

        public void close()
        {
            Console.Out.WriteLine("Już zamknięta");
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
            Program.ThreadBool = false;
            Program.State = new OpenState(Program);
        }
    }
}
