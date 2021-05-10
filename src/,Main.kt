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
                    floor[N]=1
                println(floor)
            }
            n++
        }
    }
    var nowDirection = 0 //1=up -1=down 0=stop
    var nowFloor = 1
    var floor = mutableListOf(0,0,0,0,0,0,0,0)
    val maxFloor=floor.size
    val minFloor=1
    fun CheckUp():Boolean
    {
        for(floor in nowFloor..maxFloor-1)
        {
            if(this.floor[floor]==1)
            {
                nowDirection=1
                return true
            }
            else
            {
                nowDirection=0
            }
        }
        return false
    }
    fun CheckDown():Boolean
    {
        for(floor in nowFloor-1 downTo minFloor)
        {
            if(this.floor[floor]==1)
            {
                nowDirection=-1
                return true
            }
            else
            {
                nowDirection=0
            }
        }
        return false
    }
    fun Move()
    {
        nowFloor+=nowDirection
        if(floor[nowFloor-1]==1)
        {
            floor[nowFloor-1]=0
            println("已到目標樓層 ${nowFloor}樓")
            Panel.floor.text=nowFloor.toString()
            Panel.Door.text="開門"
            Thread.sleep(3500)
            Panel.Door.text="關門"
            if(nowDirection==1)
            {
                if(!CheckUp()){
                    CheckDown()
                }

            }
            else if(nowDirection==-1)
            {
                if(!CheckDown())
                {
                    CheckUp()
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
                nowDirection=-1
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
        elevator.Move()
        Panel.floor.text=elevator.nowFloor.toString()
    }
}