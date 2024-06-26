package com.baisamy.studentsejournal.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baisamy.studentsejournal.data.api.JournalApi
import com.baisamy.studentsejournal.data.model.request.CreateCourseRequest
import com.baisamy.studentsejournal.data.model.request.CreateLessonRequest
import com.baisamy.studentsejournal.data.model.request.CreateStudentRequest
import com.baisamy.studentsejournal.data.model.request.CreateTaskRequest
import com.baisamy.studentsejournal.data.model.request.CreateUserRequest
import com.baisamy.studentsejournal.data.model.request.LoginRequest
import com.baisamy.studentsejournal.data.model.request.UpdateUserRequest
import com.baisamy.studentsejournal.data.model.response.CourseResponse
import com.baisamy.studentsejournal.data.model.response.LessonResponse
import com.baisamy.studentsejournal.data.model.response.PresenceResponse
import com.baisamy.studentsejournal.data.model.response.StudentResponse
import com.baisamy.studentsejournal.data.model.response.SubmissionResponse
import com.baisamy.studentsejournal.data.model.response.TaskResponse
import com.baisamy.studentsejournal.data.model.response.UserResponse
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _token = MutableLiveData("")
    val token: LiveData<String> = _token

    private val _courses = MutableLiveData(listOf<CourseResponse>())
    val courses: LiveData<List<CourseResponse>> = _courses

    private val _currentUser = MutableLiveData<UserResponse>()
    val currentUser: LiveData<UserResponse> = _currentUser

    private val _currentCourse = MutableLiveData<CourseResponse>()
    val currentCourse: LiveData<CourseResponse> = _currentCourse
    private val _currentCourseStudents = MutableLiveData(listOf<UserResponse>())
    val currentCourseStudents: LiveData<List<UserResponse>> = _currentCourseStudents
    private val _currentCourseLessons = MutableLiveData(listOf<LessonResponse>())
    val currentCourseLessons: LiveData<List<LessonResponse>> = _currentCourseLessons
    private val _currentCourseSubmissions = MutableLiveData(listOf<SubmissionResponse>())
    val currentCourseSubmissions: LiveData<List<SubmissionResponse>> = _currentCourseSubmissions
    private val _currentCourseUser = MutableLiveData<UserResponse>()
    val currentCourseUser: LiveData<UserResponse> = _currentCourseUser

    private val _currentLesson = MutableLiveData<LessonResponse>()
    val currentLesson: LiveData<LessonResponse> = _currentLesson
    private val _currentLessonTasks = MutableLiveData<List<TaskResponse>>()
    val currentLessonTasks: LiveData<List<TaskResponse>> = _currentLessonTasks
    private val _currentLessonPresence = MutableLiveData<List<PresenceResponse>>()
    val currentLessonPresence: LiveData<List<PresenceResponse>> = _currentLessonPresence

    private val _currentTask = MutableLiveData<TaskResponse>()
    val currentTask: LiveData<TaskResponse> = _currentTask

    private val _currentSubmission = MutableLiveData<SubmissionResponse>()
    val currentSubmission: LiveData<SubmissionResponse> = _currentSubmission

    private val _currentStudent = MutableLiveData<StudentResponse>()
    val currentStudent: LiveData<StudentResponse> = _currentStudent
    private val _currentStudentUser = MutableLiveData<UserResponse>()
    val currentStudentUser: LiveData<UserResponse> = _currentStudentUser
    private val _currentStudentAverageRate = MutableLiveData<Float>()
    val currentStudentAverageRate: LiveData<Float> = _currentStudentAverageRate

    fun authenticate(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _token.value = JournalApi.journalRepository.authenticate(loginRequest).token
            _currentUser.value = JournalApi
                .journalRepository
                .getUser(loginRequest.email, "Bearer ${_token.value}")
        }
    }

    fun register(createUserRequest: CreateUserRequest) {
        viewModelScope.launch {
            JournalApi.journalRepository.register(createUserRequest)
        }
    }

    fun deleteProfile() {
        viewModelScope.launch {
            JournalApi.journalRepository.deleteUser(
                email = currentUser.value!!.email,
                token = "Bearer ${_token.value.toString()}"
            )
        }
    }

    fun updateProfile(name: String) {
        viewModelScope.launch {
            _currentUser.value = JournalApi
                .journalRepository
                .updateUser(
                    updateUserRequest = UpdateUserRequest(
                        email = _currentUser.value!!.email,
                        name = name
                    ),
                    token = "Bearer ${_token.value.toString()}"
                )
        }
    }

    fun logOut() {
        viewModelScope.launch {
            _token.value = null
            _currentUser.value = null
        }
    }

    fun loadCourses(email: String) {
        viewModelScope.launch {
            _courses.value = JournalApi
                .journalRepository
                .loadCourses(email, "Bearer ${_token.value.toString()}")
        }
    }

    fun createCourse(name: String) {
        viewModelScope.launch {
            val createdCourse = JournalApi.journalRepository.createCourse(
                createCourseRequest = CreateCourseRequest(
                    title = name,
                    teacherEmail = _currentUser.value!!.email
                ),
                token = "Bearer ${_token.value}"
            )
            if (!_courses.value.isNullOrEmpty()) {
                _courses.value = _courses.value?.plus(createdCourse)
            } else {
                _courses.value = listOf(createdCourse)
            }
        }
    }

    fun createLesson(createLessonRequest: CreateLessonRequest) {
        viewModelScope.launch {
            val createdLesson = JournalApi.journalRepository.createLesson(
                createLessonRequest = createLessonRequest,
                token = "Bearer ${_token.value}"
            )
            val currentCourseL = _currentCourse.value
            currentCourseL!!.lessons = _currentCourse.value!!.lessons.plus(createdLesson)
            _currentCourse.value = currentCourseL
            _currentCourseLessons.value = currentCourseL.lessons
        }
    }

    fun addStudent(createStudentRequest: CreateStudentRequest) {
        viewModelScope.launch {
            val createdStudent = JournalApi.journalRepository.createStudent(
                createStudentRequest = createStudentRequest,
                token = "Bearer ${_token.value}"
            )
            val currentCourseL = _currentCourse.value
            currentCourseL!!.students = _currentCourse.value!!.students.plus(createdStudent)
            _currentCourse.value = currentCourseL
            setCurrentCourse(_currentCourse.value!!.token)
        }
    }

    fun createTask(createTaskRequest: CreateTaskRequest) {
        viewModelScope.launch {
            val createdTask = JournalApi.journalRepository.createTask(
                createTaskRequest = createTaskRequest,
                token = "Bearer ${token.value}"
            )
            val currentLessonL = _currentLesson.value
            currentLessonL!!.tasks = _currentLesson.value!!.tasks.plus(createdTask)
            _currentLesson.value = currentLessonL
            setCurrentLesson(_currentLesson.value!!.id)
        }
    }

    fun setCurrentCourse(token: String) {
        viewModelScope.launch {
            _currentCourse.value = _courses.value!!.find { it.token == token }

            _currentCourseLessons.value = _currentCourse.value!!.lessons
            val students = mutableListOf<UserResponse>()
            _currentCourse.value!!.students.forEach {
                students.add(
                    JournalApi.journalRepository.getUser(
                        email = it.studentEmail,
                        token = "Bearer ${_token.value.toString()}"
                    )
                )
            }
            _currentCourseStudents.value = students
            val submissions = mutableListOf<SubmissionResponse>()
            _currentCourse.value!!.lessons.forEach { lesson ->
                lesson.tasks.forEach { task ->
                    task.submissions.forEach { submission ->
                        submissions.add(submission)
                    }
                }
            }
            _currentCourseSubmissions.value = submissions
            _currentCourseUser.value = JournalApi.journalRepository.getUser(
                email = _currentCourse.value!!.teacherEmail,
                token = "Bearer ${_token.value}"
            )
        }
    }

    fun setCurrentLesson(id: Long) {
        viewModelScope.launch {
            _currentLesson.value = _currentCourse.value!!.lessons.find { it.id == id }
            _currentLessonTasks.value = _currentLesson.value!!.tasks
            _currentLessonPresence.value = _currentLesson.value!!.studentsPresence
        }
    }

    fun setCurrentTask(id: Long) {
        viewModelScope.launch {
            _currentTask.value = _currentLesson.value!!.tasks.find { it.id == id }
        }
    }

    fun setCurrentSubmission(id: Long) {
        viewModelScope.launch {
            _currentSubmission.value = _currentTask.value!!.submissions.find { it.id == id }
        }
    }

    fun setCurrentStudent(id: Long) {
        viewModelScope.launch {
            _currentStudent.value = _currentCourse.value!!.students.find { it.id == id }
            val submissions = mutableListOf<SubmissionResponse>()
            _currentCourseSubmissions.value!!.forEach {
                if (it.studentEmail == _currentStudent.value!!.studentEmail)
                    submissions.add(it)
            }
            var sum = 0f
            var average = 0f
            submissions.forEach {
                if (it.mark.rating != null && it.mark.maxRating != null)
                    sum += it.mark.rating / it.mark.maxRating * 100
            }
            if (submissions.isNotEmpty())
                average = sum / submissions.size
            _currentStudentAverageRate.value = average
            _currentStudentUser.value = JournalApi.journalRepository.getUser(
                _currentStudent.value!!.studentEmail,
                "Bearer ${_token.value}"
            )
        }
    }

}