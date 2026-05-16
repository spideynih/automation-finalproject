# Final Project QA – LMS B2B Dibimbing

![CI/CD](https://github.com/spideynih/FinalProject-QA/actions/workflows/automation.yml/badge.svg)

Project ini merupakan bagian dari Bootcamp Quality Assurance – dibimbing.id.  
Fokus project ini adalah **UI & API Automation Testing** menggunakan **Selenium** dan **RestAssured** dengan pendekatan **Page Object Model (POM)**.  
Test yang diimplementasikan adalah **happy flow (positive scenario)**.

---

## Application Under Test
Final Project QA akan menggunakan platform **LMS B2B Dibimbing** sebagai objek pengujian:

- Website: [LMS B2B Dibimbing Login](https://lms-b2b.do.dibimbing.id/dibimbingqa/login)  
- Dokumentasi API: [LMS B2B GraphQL API](https://lmsb2b.do.dibimbing.id/graphql)  

---

## Test Coverage (Happy Flow)

### Login Test

| Test Case ID | Scenario | Expected Result |
|--------------|---------|----------------|
| TC_LOGIN_001 | Login dengan email & password valid | Berhasil masuk ke dashboard |

### Employee Management Test

| Test Case ID | Scenario | Expected Result |
|--------------|---------|----------------|
| TC_EMP_001 | Add Employee dengan data valid | Employee berhasil ditambahkan |
| TC_EDIT_EMP_001 | Edit Employee | Employee berhasil diupdate |
| TC_DEL_EMP_001 | Delete Employee | Employee berhasil dihapus |

### Employee API Test

| Test Case ID | Scenario | Expected Result |
|--------------|---------|----------------|
| API_LOGIN_001 | Login API | Berhasil login dengan status code 200 |
| API_EMP_001 | Add Employee API | Employee berhasil dibuat dengan ID unik |
| API_EMP_002 | Edit Employee API | Employee berhasil diupdate |
| API_EMP_003 | Delete Employee API | Employee berhasil dihapus |
