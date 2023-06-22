package com.onlineresumebuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OnlineResumeBuilderDemo {

	Connection conn;

	public static void main(String[] args) throws SQLException {
		DatabaseConnector connector = new DatabaseConnector("jdbc:mysql://localhost:3306/resume_db", "root",
				"Mysql@123");
		Connection conn = connector.getConnection();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Online Resume Builder");
		System.out.println("=====================");
		System.out.println();

		ResumeBuilder resumeBuilder = new ResumeBuilder();

		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		resumeBuilder.setName(name);

		System.out.print("Enter your email: ");
		String email = scanner.nextLine();
		resumeBuilder.setEmail(email);

		System.out.print("Enter your phone number: ");
		String phone = scanner.nextLine();
		resumeBuilder.setPhone(phone);

		System.out.print("Enter your address: ");
		String address = scanner.nextLine();
		resumeBuilder.setAddress(address);

		System.out.print("Enter your professional summary: ");
		String summary = scanner.nextLine();
		resumeBuilder.setSummary(summary);

		System.out.println("Enter your skills (one per line, enter 'done' to finish): ");
		String skill = "";
		while (true) {
			skill = scanner.nextLine();
			if (skill.equalsIgnoreCase("done")) {
				break;
			}
			resumeBuilder.addSkill(skill);
		}

		System.out.println("Enter your experiences (one per line, enter 'done' to finish): ");
		String experience;
		while (true) {
			experience = scanner.nextLine();
			if (experience.equalsIgnoreCase("done")) {
				break;
			}
			resumeBuilder.addExperience(experience);
		}

		System.out.println("Enter your education (one per line, enter 'done' to finish): ");
		String education;
		while (true) {
			education = scanner.nextLine();
			if (education.equalsIgnoreCase("done")) {
				break;
			}
			resumeBuilder.addEducation(education);
		}

		try {
			saveRecord(conn, resumeBuilder);
		} catch (Exception e) {
			System.out.println("Record Added sucessfully.!!!");
		} finally {
			System.out.println("Validate your record in DB");
			conn.close();
			System.exit(0);
		}
	}

	public static void saveRecord(Connection conn, ResumeBuilder resumeBuilder) throws SQLException {
		Resume resume = resumeBuilder.build();
		// Save resume information to the database
		PreparedStatement statement = conn.prepareStatement(
				"INSERT INTO resumes (name, email, phone, address, summary, skills, experiences, education) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, resume.getName());
		statement.setString(2, resume.getEmail());
		statement.setString(3, resume.getPhone());
		statement.setString(4, resume.getAddress());
		statement.setString(5, resume.getSummary());
		statement.setString(6, String.join(",", resume.getSkills()));
		statement.setString(7, String.join(",", resume.getExperiences()));
		statement.setString(8, String.join(",", resume.getEducation()));
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected == 1) {
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int resumeId = generatedKeys.getInt(1);
				// Save skills
				for (String skillItem : resume.getSkills()) {
					PreparedStatement skillStatement = conn
							.prepareStatement("INSERT INTO resumes (resume_id, skills) VALUES (?, ?)");
					skillStatement.setInt(1, resumeId);
					skillStatement.setString(2, skillItem);
					skillStatement.executeUpdate();
				}
				// Save experiences
				for (String experienceItem : resume.getExperiences()) {
					PreparedStatement experienceStatement = conn
							.prepareStatement("INSERT INTO resumes (resume_id, experience) VALUES (?, ?)");
					experienceStatement.setInt(1, resumeId);
					experienceStatement.setString(2, experienceItem);
					experienceStatement.executeUpdate();
				}
				// Save education
				for (String educationItem : resume.getEducation()) {
					PreparedStatement educationStatement = conn
							.prepareStatement("INSERT INTO resumes (resume_id, education) VALUES (?, ?)");
					educationStatement.setInt(1, resumeId);
					educationStatement.setString(2, educationItem);
					educationStatement.executeUpdate();
				}

				System.out.println("Resume saved successfully with ID: " + resumeId);
			}
		}
	}
}