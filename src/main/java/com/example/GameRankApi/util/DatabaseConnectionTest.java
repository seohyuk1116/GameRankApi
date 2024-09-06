//package com.example.GameRankApi.util;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.yaml.snakeyaml.Yaml;
//
//import javax.sql.DataSource;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Map;
//
//@Configuration
//@EnableConfigurationProperties
////public class DatabaseConnectionTest {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    public static void main(String[] args) {
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConnectionTest.class)) {
//            DataSource dataSource = context.getBean(DataSource.class);
//
//            try (Connection conn = dataSource.getConnection()) {
//                System.out.println("데이터베이스 연결 성공!");
//
//                // 데이터베이스 메타데이터 정보 출력
//                System.out.println("데이터베이스: " + conn.getMetaData().getDatabaseProductName());
//                System.out.println("버전: " + conn.getMetaData().getDatabaseProductVersion());
//
//                // 간단한 쿼리 실행
//                try (Statement stmt = conn.createStatement()) {
//                    ResultSet rs = stmt.executeQuery("SELECT 1 as test");
//                    if (rs.next()) {
//                        System.out.println("쿼리 테스트 결과: " + rs.getInt("test"));
//                    }
//                }
//
//                // 사용자 데이터 테이블 존재 여부 확인
//                try (ResultSet tables = conn.getMetaData().getTables(null, null, "user_data", null)) {
//                    if (tables.next()) {
//                        System.out.println("user_data 테이블이 존재합니다.");
//                    } else {
//                        System.out.println("user_data 테이블이 존재하지 않습니다.");
//                    }
//                }
//
//                // 사용자 점수 테이블 존재 여부 확인
//                try (ResultSet tables = conn.getMetaData().getTables(null, null, "user_score", null)) {
//                    if (tables.next()) {
//                        System.out.println("user_score 테이블이 존재합니다.");
//                    } else {
//                        System.out.println("user_score 테이블이 존재하지 않습니다.");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("데이터베이스 연결 실패!");
//            e.printStackTrace();
//        }
//    }
//
//    // application.yml 파일 읽기
//    private static Map<String, Object> loadYamlConfig() {
//        Yaml yaml = new Yaml();
//        try (InputStream inputStream = DatabaseConnectionTest.class.getClassLoader().getResourceAsStream("application.yml")) {
//            return yaml.load(inputStream);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load application.yml", e);
//        }
//    }
//}