package bench.keywar.Main

import android.content.Intent
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bench.keywar.ChooseCount.ChooseCountDialog
import bench.keywar.Practice.PracticeActivity
import bench.keywar.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainPresenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(presenter) {
            main_btn_single.setOnClickListener {
                showSingleDialog()
            }
        }
    }

    override fun showSingleDialog() {
        val dialog = ChooseCountDialog(this)
        dialog.show()
        dialog.setOnDismissListener {
            if (!dialog.sentenceCount.isNullOrBlank()) {
                showToast("된다")
                val res = presenter.getSingleString(dialog.sentenceCount!!)
                val intent = Intent(baseContext, PracticeActivity::class.java)
                intent.putStringArrayListExtra("sentences", res)
                startActivity(intent)
            }
        }
    }

    override fun showToast(content: String) {
        Toast.makeText(baseContext, content, Toast.LENGTH_SHORT).show()

    }
}
