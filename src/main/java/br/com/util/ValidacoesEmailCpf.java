package br.com.util;

public class ValidacoesEmailCpf {
    // validação para o cpf
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;

        // remove tudo que não for número
        cpf = cpf.replaceAll("\\D", "");

        // deve ter 11 dígitos
        if (cpf.length() != 11) return false;

        // elimina CPFs com todos os dígitos iguais
        String finalCpf = cpf;
        if (cpf.chars().allMatch(c -> c == finalCpf.charAt(0))) return false;

        try {
            // cálculo do 1º dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int dig1 = 11 - (soma % 11);
            if (dig1 >= 10) dig1 = 0;

            // cálculo do 2º dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int dig2 = 11 - (soma % 11);
            if (dig2 >= 10) dig2 = 0;

            // verifica se bate com os dígitos do CPF
            return dig1 == (cpf.charAt(9) - '0') &&
                    dig2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }

    // validação para o email
    public static boolean validarEmail(String email) {
        if (email == null || email.isBlank()) return false;

        // regex básica
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(regex)) return false;

        // regras extras
        if (email.contains("..")) return false; // evita dois pontos seguidos
        if (email.startsWith(".") || email.endsWith(".")) return false;

        return true;
    }
}
