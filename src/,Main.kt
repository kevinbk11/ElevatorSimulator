import GUI.*
import java.text.BreakIterator


class Elevator
{

    init{
        ControlTable.contentPane=Panel.panel
        ControlTable.isResizable=false
        ControlTable.setSize(400,500)
        ControlTable.isVisible=true
        Panel.floor.text="1"
        var n=0
        for(btn in btnList)
        {
            var N=n
            btn.addActionListener {
                if(floor[N]!=2)
                {
                    floor[N]=1
                }
                println(floor)
            }
            n++
        }
    }
    var nowDirection = 0 //1=up 2=down 0=stop
    var nowFloor = 1
    var floor = mutableListOf(2,0,0,0,0,0,0,0)
    val maxFloor=floor.size
    val minFloor=1
    fun Move()
    {
        if (nowDirection==1 && nowFloor!=maxFloor)
        {
            floor[nowFloor-1]=0
            nowFloor+=1
            print(nowFloor)
            if(floor[nowFloor-1]==1)
            {
                floor[nowFloor-1]=2
                println("已到目標樓層 ${nowFloor}樓")
                Panel.floor.text=nowFloor.toString()
                Panel.Door.text="開門"
                Thread.sleep(3500)
                Panel.Door.text="關門"
                for(floor in nowFloor-1..maxFloor)
                {
                    if(this.floor[floor-1]==1)
                    {
                        nowDirection=1
                        break
                    }
                    else
                    {
                        nowDirection=0
                    }
                }
            }
        }
        else if(nowDirection==2 && nowFloor!=minFloor)
        {
            floor[nowFloor-1]=0
            nowFloor-=1
            if(floor[nowFloor-1]==1)
            {
                floor[nowFloor-1]=2
                println("已到目標樓層 ${nowFloor}樓")
                Panel.floor.text=nowFloor.toString()
                Panel.Door.text="開門"
                Thread.sleep(3500)
                Panel.Door.text="關門"
                for(floor in nowFloor-1 downTo minFloor)
                {
                    if(this.floor[floor]==1)
                    {
                        nowDirection=2
                        break
                    }
                    else
                    {
                        nowDirection=0
                    }
                }
            }
        }
        println("目前樓層${this.nowFloor}")
    }
    fun Check()
    {
        for(floor in 0..7)
        {
            if(this.floor[floor]==1 && floor>nowFloor)
            {
                nowDirection=1
                break
            }
            else if(this.floor[floor]==1 && floor<=nowFloor)
            {
                nowDirection=2
                break
            }
        }
    }
}
fun main(args:Array<String>)
{
    var elevator=Elevator()
    while(true)
    {
        if(elevator.nowDirection==0)
        {
            elevator.Check()

        }
        Thread.sleep(1000)
        if(elevator.nowDirection==1)
        {
            for(floor in elevator.nowFloor..elevator.maxFloor-1)
            {
                if(elevator.floor[floor]==1)
                {
                    elevator.Move()
                    break
                }
            }
        }
        else if(elevator.nowDirection==2)
        {
            for(floor in elevator.nowFloor-1 downTo elevator.minFloor-1)
            {
                if(elevator.floor[floor]==1)
                {
                    elevator.Move()
                    break
                }
            }
        }
        Panel.floor.text=elevator.nowFloor.toString()
    }
}