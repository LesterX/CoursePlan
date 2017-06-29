public class Session 
{
	Time t[] = new Time[5];
	int t_num = 0;
	String lecturer;
	
	public Session()
	{
		lecturer = "";
	}
	
	public void set_lecturer(String l)
	{
		lecturer = l;
	}
	
	public void add_time(String d, int s, int e)
	{
		if (t_num == 5)
			return;
		
		t[t_num] = new Time(d,s,e);
		t_num ++;
	}
	
	public boolean is_collision(Session sess)
	{
		for (Time time0 : t)
		{
			for (Time time1: sess.t)
			{
				if (time0 != null && time1 != null && time0.day.equals(time1.day))
				{
					if (!(time0.start >= time1.end || time0.end <= time1.start))
						return true;
				}
			}
		}
		
		return false;
	}
}
