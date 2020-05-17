package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the objects
			Instructor tempInstructor = new Instructor("Maruf", "Monem", "Anik@yahoo.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("Learn with a cs grad", "Video games");
			
//			Instructor tempInstructor = new Instructor("Jacob", "Yuri", "straightPipes@yahoo.com");
//			InstructorDetail tempInstructorDetail = new InstructorDetail("Straight Pipers", "Reviewing Cars");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
					
			//start a transaction
			session.beginTransaction();

			//save the instructor
			//this will also save the details object because of cascadeType.ALL
			//it would save the object and any other objects associated with it
			System.out.println("Saving Instructor: " + tempInstructor);
			session.save(tempInstructor); 
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} finally {
			factory.close();
		}
	}

}
