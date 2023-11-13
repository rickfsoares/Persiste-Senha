package br.edu.ifpb.gerasenha

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable
import kotlin.random.Random
import android.view.View.OnClickListener

class NewPassword: AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var quantityCharacters: TextView
    private lateinit var checkBoxCapitalize: CheckBox
    private lateinit var checkBoxNumbers: CheckBox
    private lateinit var checkBoxSymbols: CheckBox
    private lateinit var description: EditText
    private lateinit var toGeneratePassword: Button
    private lateinit var cancel: Button
    private lateinit var edit: Button
    private lateinit var delete: Button
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        val intent = intent

        this.seekBar = findViewById(R.id.seekBar)
        this.quantityCharacters = findViewById(R.id.tvMidStatus)

        this.checkBoxCapitalize = findViewById(R.id.cbCapitalized)
        this.checkBoxNumbers = findViewById(R.id.cbNumbers)
        this.checkBoxSymbols = findViewById(R.id.cbSymbols)

        this.description = findViewById(R.id.etDesc)

        this.toGeneratePassword = findViewById(R.id.bGenerete)

        this.cancel = findViewById(R.id.bCancel)
        this.edit = findViewById(R.id.bEdit)
        this.delete = findViewById(R.id.bDelete)
        this.title = findViewById(R.id.tvDesc)

        this.cancel.setOnClickListener {
            setResult(RESULT_CANCELED)
        }

        this.delete.setOnClickListener {
            val intent = Intent().apply {
                putExtra("delete", true)
            }

            setResult(RESULT_OK, intent)
            finish()
        }

        var passwd = getSerializable(this, "password", Password::class.java)

        this.edit.setOnClickListener {
            if(this.description.text.toString() != passwd.getDescription()) {
                passwd.setDescription(this.description.text.toString())
            }

            if(this.seekBar.progress != passwd.getGeneratedPassword().length ||
                this.checkBoxCapitalize.isChecked != passwd.getHasCapitalize() ||
                this.checkBoxNumbers.isChecked != passwd.getHasNumbers() ||
                this.checkBoxSymbols.isChecked != passwd.getHasSymbols()) {
                passwd.setGeneratedPassword(this.generatePassword())

                Log.i("app", passwd.getGeneratedPassword())
            }

            val intent = Intent().apply {
                putExtra("edit", true)
                putExtra("passwd", passwd)
            }

            setResult(RESULT_OK, intent)
            finish()
        }

        this.toGeneratePassword.setOnClickListener(GeneratePasswd())

        if(intent.getStringExtra("screenType").equals("creation")) {
            this.title.text = "Nova Senha"
            this.edit.visibility = View.GONE
            this.delete.visibility = View.GONE
        } else {
            this.toGeneratePassword.visibility = View.GONE
            this.title.text = "Alterar Senha"
            this.description.setText(passwd.getDescription())
            this.seekBar.progress = passwd.getGeneratedPassword().length
            this.quantityCharacters.text = passwd.getGeneratedPassword().length.toString()
            this.checkBoxCapitalize.isChecked = passwd.getHasCapitalize()
            this.checkBoxNumbers.isChecked = passwd.getHasNumbers()
            this.checkBoxSymbols.isChecked = passwd.getHasSymbols()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Atualize o valor do TextView com o valor atual do SeekBar
                quantityCharacters.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Este método é chamado quando o usuário toca no SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Este método é chamado quando o usuário solta o dedo do SeekBar
            }
        })

    }

    fun generatePassword(): String {
        val capitalize =  this.checkBoxCapitalize.isChecked
        val numeric = this.checkBoxNumbers.isChecked
        val symbol = this.checkBoxSymbols.isChecked
        val upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numberChars = "0123456789"
        val specialChars = "!@#$%^&*()_-+=<>?/[]{}|"

        val allowedChars = StringBuilder()

        if (capitalize) allowedChars.append(upperCaseChars)
        if (numeric) allowedChars.append(numberChars)
        if (symbol) allowedChars.append(specialChars)

        val random = Random.Default
        var result = (1..this@NewPassword.seekBar.progress)
            .map { allowedChars[random.nextInt(allowedChars.length)] }
            .joinToString("")

        return result

    }
    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }
    inner class GeneratePasswd: OnClickListener {
        override fun onClick(p0: View?) {
            var result = this@NewPassword.generatePassword()

            val description = this@NewPassword.description.text.toString()

            var senha = Password(result, description,
                this@NewPassword.checkBoxCapitalize.isChecked,
                this@NewPassword.checkBoxNumbers.isChecked,
                this@NewPassword.checkBoxCapitalize.isChecked)


            val intent = Intent().apply {
                putExtra("senha", senha)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}