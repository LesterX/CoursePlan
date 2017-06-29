import java.util.*;
import java.io.*;

public class Plan 
{	
	public static void main(String[] args) throws FileNotFoundException
	{
		Course courses[] = new Course[12];
		int c_num = 0;
		
		// To read from a file
		//InputStream f = new FileInputStream(new File("input.txt")); 
		//Scanner s = new Scanner(f);

		
		// To read from user input
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter a number: ");
		System.out.println("  1. Add a course");
		System.out.println("  2. Delete a course");
		System.out.println("  3. Show the courses");
		System.out.println("  4. Make plans");
		System.out.println("  5. Quit");
		
		while (true)
		{
			if (!s.hasNext())
				break;
			
			String in = s.nextLine();
			int in_num = Integer.parseInt(in);
			
			if (in_num == 5)
				break;
			
			switch(in_num)
			{
				case 1:
				{
					System.out.println("Enter the name of the course: ");
					String c_name = s.nextLine();
					Course c = new Course(c_name);
					int sess_num = 0;
					System.out.println("Enter a number: ");
					System.out.println("  1. Add a session");
					System.out.println("  2. Delete a session");
					System.out.println("  3. Show the sessions");
					System.out.println("  4. Session done");					
					while (true)
					{
						String in_2 = s.nextLine();
						int in_2_num = Integer.parseInt(in_2);
						
						if (in_2_num == 4)
							break;
						
						if (in_2_num == 1)
						{
							sess_num ++;
							Session sess = new Session();
							System.out.println("Session " + sess_num);
							System.out.println("Enter a number: ");
							System.out.println("  1. Add a time period");
							System.out.println("  2. Time periods done");
								
							while (true)
							{
								String in_3 = s.nextLine();
								int in_3_num = Integer.parseInt(in_3);
								
								if (in_3_num == 2)
									break;
								else if (in_3_num != 1)
								{
									System.out.println("Invalid input");
									break;
								}else
								{
									System.out.println("Enter the day of a week: ");
									String day = s.nextLine();
									System.out.println("Enter the starting time: ");
									int start =Integer.parseInt(s.nextLine());
									System.out.println("Enter the ending time: ");
									int end = Integer.parseInt(s.nextLine());
									sess.add_time(day, start, end);
								}
									
								System.out.println("Enter a number: ");
								System.out.println("  1. Add a time period");
								System.out.println("  2. Time periods done");
							}
							c.add_session(sess);
						}else if (in_2_num == 2)
						{
							System.out.println("Here is the sessions been entered");
							c.show_session();
							System.out.println("Which session do you want to delete (index)?");
							String in_i = s.nextLine();
							int in_i_num = Integer.parseInt(in_i);
							if (in_i_num > c.ss_num)
							{
								System.out.println("Invalid input");
								break;
							}
							c.delete_session(in_i_num);
						}else if (in_2_num == 3)
						{
							c.show_session();
						}else
							System.out.println("Invalid input");
						
						System.out.println("Enter a number: ");
						System.out.println("  1. Add a session");
						System.out.println("  2. Delete a session");
						System.out.println("  3. Show the sessions");
						System.out.println("  4. Session done");	
					}
					courses[c_num] = c;
					c_num ++;
					break;
				}
				
				case 2:
				{
					for (int i = 0; i < c_num; i ++)
					{
						for (int j = i + 1; j < c_num; j ++)
						{
							if (courses[i] != null && courses[j] != null)
							{
								if (courses[i].ss_num > courses[j].ss_num)
								{
									Course temp = courses[i];
									courses[i] = courses[j];
									courses[j] = temp;
								}
							}
						}
					}
					
					int count = 1;

					for (Course cou : courses)
					{
						if (cou != null)
						{
							System.out.print(count + ".  ");
							cou.show_session();
							count ++;
						}
					}
					
					System.out.println("Which course do you want to delete (index) ?");
					String in_i = s.nextLine();
					int in_i_num = Integer.parseInt(in_i);
					for (int i = in_i_num - 1; i < c_num - 1; i ++)
						courses[i] = courses[i + 1];
					
					courses[c_num - 1] = null;
					break;
				}
				
				case 3:
				{
					for (int i = 0; i < c_num; i ++)
					{
						for (int j = i + 1; j < c_num; j ++)
						{
							if (courses[i] != null && courses[j] != null)
							{
								if (courses[i].ss_num > courses[j].ss_num)
								{
									Course temp = courses[i];
									courses[i] = courses[j];
									courses[j] = temp;
								}
							}
						}
					}
					
					int count = 1;

					for (Course cou: courses)
					{	
						if (cou != null)
						{
							System.out.print(count + ".  ");
							cou.show_session();
							count ++;
						}
					}
					break;
				}
				
				case 4:
				{
					// If there is no course, break
					if (courses[0] == null)
					{
						System.out.println("Please enter at least one course");
						break;
					}
					
					// Sort the courses in the order of the number of sessions
					for (int i = 0; i < c_num; i ++)
					{
						for (int j = i + 1; j < c_num; j ++)
						{
							if (courses[i] != null && courses[j] != null)
							{
								if (courses[i].ss_num > courses[j].ss_num)
								{
									Course temp = courses[i];
									courses[i] = courses[j];
									courses[j] = temp;
								}
							}
						}
					}
					
					// Two dimensional array for the plans
					Session plans[][] = new Session[30][12];
					int plan_num = 0;
					
					// Set the index
					int pointer = 0;
					int index[] = new int[12];
					
					while (true)
					{	
						// If all sessions are checked, end the loop
						if (pointer == 0 && index[0] == courses[0].ss_num)
							break;
						
						Session plan[] = new Session[12];
						int count = 0;
						
						while (true)
						{
							if (pointer == 0 && index[0] == courses[0].ss_num)
								break;
							
							Course c = courses[pointer];
							Session toadd = c.ss[index[pointer]];
							
							if (count == 0)
							{
								plan[count] = toadd;
								pointer ++;
								count ++;
							}else
							{
								boolean collision = false;
								
								for (int j = 0; j < pointer; j ++)
								{
									Session comp = plan[j];
									if (comp.is_collision(toadd))
									{	
										collision = true;
										break;
									}
								}
								
								if (!collision)
								{
									plan[pointer] = toadd;

									if (pointer < c_num - 1)
									{
										pointer ++;
										count ++;
									}else
									{
										Session ss_toadd[] = new Session[12];
										for (int h = 0; h < c_num; h ++)
											ss_toadd[h] = plan[h];
										
										plans[plan_num] = ss_toadd;
										plan_num ++;
										index[pointer] ++;
									}
								}else
									index[pointer] ++;
							}
							
							while (pointer > 0 && index[pointer] == courses[pointer].ss_num)
							{
								for (int x = pointer; x < c_num; x ++)
									index[x] = 0;
								
								pointer --;
								index[pointer] ++;
								plan[pointer] = null;
							}
						}
					}

					int last_ct = 1;
					for (int q = 0; q < plan_num; q ++)
					{
						Session[] p = plans[q];
						
						if (p != null)
						{
							System.out.println("");
							System.out.println("Plan " + last_ct);
							for (int k = 0; k < c_num; k ++)
							{
								Session sess = p[k];
								if (sess != null)
								{
									System.out.println("  " + courses[k].name);

									for (Time ti : sess.t)
									{
										if (ti != null)
											System.out.println("    " + ti.day + "  " + ti.start + " ~ " + ti.end);
									}
									
									System.out.println("");
								}
							}
						}
						
						last_ct ++;
					}
					
					break;
				}
				
				default:
				{
					System.out.println("Invalid input");
					break;
				}
			}
			
			System.out.println("Enter a number: ");
			System.out.println("  1. Add a course");
			System.out.println("  2. Delete a course");
			System.out.println("  3. Show the courses");
			System.out.println("  4. Make plans");
			System.out.println("  5. Quit");
		}
	}
}