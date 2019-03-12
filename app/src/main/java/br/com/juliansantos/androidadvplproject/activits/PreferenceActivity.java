package br.com.juliansantos.androidadvplproject.activits;

import android.os.Bundle;
import android.preference.PreferenceManager;

import br.com.juliansantos.androidadvplproject.R;

public class PreferenceActivity extends android.preference.PreferenceActivity {

    public static final String PREF_KEY_USER_CODE = "user_code";
    public static final String PREF_KEY_USER_PASS = "user_pass";
    public static final String PREF_KEY_SAVE_LOGIN = "save_login";
    public static final String PREF_KEY_SERVER = "pref_server";
    public static final String PREF_KEY_PORT = "pref_port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * @see https://developer.android.com/reference/android/preference/PreferenceActivity.html#addPreferencesFromResource(int)
         * Infla o recurso XML especificado e adiciona a hierarquia de preferências à hierarquia de preferências atual.
         */
        addPreferencesFromResource(R.xml.preferences);

        /**
         * @see https://developer.android.com/reference/android/preference/PreferenceManager.html#setDefaultValues(android.content.Context,%20int,%20boolean)
         * Define os valores padrão de um arquivo de preferências XML,
         * lendo os valores definidos pelo atributo de cada Preferenceitem android:defaultValue.
         * Isso deve ser chamado pela atividade principal do aplicativo.
         */
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }
}
