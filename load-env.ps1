# Load variables from .env into current PowerShell session

Get-Content ".env" | ForEach-Object {

    if ($_ -match '^\s*#') { return }
    if ([string]::IsNullOrWhiteSpace($_)) { return }

    $parts = $_ -split '=', 2

    if ($parts.Count -eq 2) {
        $name = $parts[0].Trim()
        $value = $parts[1].Trim()

        Set-Item -Path "Env:$name" -Value $value
    }
}

Write-Host "Environment variables loaded from .env"