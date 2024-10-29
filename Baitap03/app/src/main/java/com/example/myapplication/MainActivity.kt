import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import model.Student
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: ArrayAdapter<String>
    private val studentList = listOf(
        Student("Nguyen Van A", "123456"),
        Student("Le Thi B", "654321"),
        Student("Tran Van C", "112233")
        // Thêm các sinh viên khác
    )

    private val displayList = studentList.map { "${it.name} - ${it.mssv}" }.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = studentAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
                    }.map { "${it.name} - ${it.mssv}" }
                    updateListView(filteredList)
                } else {
                    updateListView(displayList)
                }
            }
        })
    }

    private fun updateListView(newList: List<String>) {
        studentAdapter.clear()
        studentAdapter.addAll(newList)
        studentAdapter.notifyDataSetChanged()
    }
}