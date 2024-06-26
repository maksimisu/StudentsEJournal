package com.baisamy.studentsejournal.data.repository

import com.baisamy.studentsejournal.data.model.request.CreateCourseRequest
import com.baisamy.studentsejournal.data.model.request.CreateLessonRequest
import com.baisamy.studentsejournal.data.model.request.CreateNoteRequest
import com.baisamy.studentsejournal.data.model.request.CreateStudentRequest
import com.baisamy.studentsejournal.data.model.request.CreateSubmissionRequest
import com.baisamy.studentsejournal.data.model.request.CreateTaskRequest
import com.baisamy.studentsejournal.data.model.request.CreateUserRequest
import com.baisamy.studentsejournal.data.model.request.LoginRequest
import com.baisamy.studentsejournal.data.model.request.UpdateCourseRequest
import com.baisamy.studentsejournal.data.model.request.UpdateLessonRequest
import com.baisamy.studentsejournal.data.model.request.UpdateNoteRequest
import com.baisamy.studentsejournal.data.model.request.UpdateStudentRequest
import com.baisamy.studentsejournal.data.model.request.UpdateSubmissionRequest
import com.baisamy.studentsejournal.data.model.request.UpdateTaskRequest
import com.baisamy.studentsejournal.data.model.request.UpdateUserRequest
import com.baisamy.studentsejournal.data.model.response.CourseResponse
import com.baisamy.studentsejournal.data.model.response.LessonResponse
import com.baisamy.studentsejournal.data.model.response.NoteResponse
import com.baisamy.studentsejournal.data.model.response.StudentResponse
import com.baisamy.studentsejournal.data.model.response.SubmissionResponse
import com.baisamy.studentsejournal.data.model.response.TaskResponse
import com.baisamy.studentsejournal.data.model.response.TokenResponse
import com.baisamy.studentsejournal.data.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JournalRepository {

    @POST("auth")
    suspend fun authenticate(
        @Body loginRequest: LoginRequest
    ): TokenResponse

    @POST("user")
    suspend fun register(
        @Body createUserRequest: CreateUserRequest
    )

    @GET("user/{email}")
    suspend fun getUser(
        @Path("email") email: String,
        @Header("Authorization") token: String
    ): UserResponse

    @GET("course/user/{email}")
    suspend fun loadCourses(
        @Path("email") email: String,
        @Header("Authorization") token: String
    ): List<CourseResponse>

    @POST("course")
    suspend fun createCourse(
        @Body createCourseRequest: CreateCourseRequest,
        @Header("Authorization") token: String
    ): CourseResponse

    @POST("course/lesson")
    suspend fun createLesson(
        @Body createLessonRequest: CreateLessonRequest,
        @Header("Authorization") token: String
    ): LessonResponse

    @POST("course/task")
    suspend fun createTask(
        @Body createTaskRequest: CreateTaskRequest,
        @Header("Authorization") token: String
    ): TaskResponse

    @POST("course/submission")
    suspend fun createSubmission(
        @Body createSubmissionRequest: CreateSubmissionRequest,
        @Header("Authorization") token: String
    ): SubmissionResponse

    @POST("course/note")
    suspend fun createNote(
        @Body createNoteRequest: CreateNoteRequest,
        @Header("Authorization") token: String
    ): NoteResponse

    @POST("course/student")
    suspend fun createStudent(
        @Body createStudentRequest: CreateStudentRequest,
        @Header("Authorization") token: String
    ): StudentResponse

    @PUT("course")
    suspend fun updateCourse(
        @Body updateCourseRequest: UpdateCourseRequest,
        @Header("Authorization") token: String
    ): CourseResponse

    @PUT("course/lesson")
    suspend fun updateLesson(
        @Body updateLessonRequest: UpdateLessonRequest,
        @Header("Authorization") token: String
    ): LessonResponse

    @PUT("course/task")
    suspend fun updateTask(
        @Body updateTaskRequest: UpdateTaskRequest,
        @Header("Authorization") token: String
    ): TaskResponse

    @PUT("course/submission")
    suspend fun updateSubmission(
        @Body updateSubmissionRequest: UpdateSubmissionRequest,
        @Header("Authorization") token: String
    ): SubmissionResponse

    @PUT("course/note")
    suspend fun createNote(
        @Body updateNoteRequest: UpdateNoteRequest,
        @Header("Authorization") token: String
    ): NoteResponse

    @PUT("course/student")
    suspend fun updateStudent(
        @Body updateStudentRequest: UpdateStudentRequest,
        @Header("Authorization") token: String
    ): StudentResponse

    @PUT("user/")
    suspend fun updateUser(
        @Body updateUserRequest: UpdateUserRequest,
        @Header("Authorization") token: String
    ): UserResponse

    @DELETE("course/{token}")
    suspend fun deleteCourse(
        @Path("token") courseToken: String,
        @Header("Authorization") token: String
    )

    @DELETE("course/lesson/{id}")
    suspend fun deleteLesson(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @DELETE("course/task/{id}")
    suspend fun deleteTask(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @DELETE("course/submission/{id}")
    suspend fun deleteSubmission(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @DELETE("course/note/{id}")
    suspend fun deleteNote(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @DELETE("course/student/{id}")
    suspend fun deleteStudent(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @DELETE("user/{email}")
    suspend fun deleteUser(
        @Path("email") email: String,
        @Header("Authorization") token: String
    )
}